package com.simol.ounuser.user.service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClient;

import com.simol.ouncommon.auth.entity.UserEntity;
import com.simol.ouncommon.auth.repository.UsersRepository;
import com.simol.ouncommon.auth.vo.AuthTokenResponse;
import com.simol.ouncommon.auth.vo.Token;
import com.simol.ounuser.config.jwt.JwtProvider;
import com.simol.ounuser.user.vo.GoogleUserInfoResponse;
import com.simol.ounuser.user.vo.RedirectUrlResponse;
import com.simol.ounuser.user.vo.UserInfoResponse;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class AuthService {
    private final JwtProvider jwtProvider;
    private final RestClient restClient;
    private final UsersRepository usersRepository;
    private final RedisTemplate<String, Object> redisTemplate;

    private final long REFRESH_TOKEN_EXPIRATION_TIME = 360L;
    private final long ACCESS_TOKEN_EXPIRATION_TIME = 60L;

    @Value("${oauth2.google.client-id}")
    private String clientId;

    @Value("${oauth2.google.client-secret}")
    private String clientSecret;

    @Transactional
    public AuthTokenResponse authenticateWithGoogle(String googleToken) {
        log.debug("accessToken: {}", googleToken);
        final String GOOGLE_USER_INFO_URL = "https://www.googleapis.com/oauth2/v1/userinfo?access_token=%s".formatted(googleToken);
        // 구글에서 계정 정보 받아오기
        GoogleUserInfoResponse googleUserInfoResponse = restClient.get().uri(GOOGLE_USER_INFO_URL)
            .retrieve()
            .body(GoogleUserInfoResponse.class);
        // 유저 정보 확인 후 저장하기
        UserEntity user = usersRepository.findByEmail(googleUserInfoResponse.getEmail())
            .orElseGet(() -> 
                UserEntity.create(
                    googleUserInfoResponse.getEmail(), 
                    googleUserInfoResponse.getName(), 
                    googleUserInfoResponse.getPicture(), 
                    "ROLE_USER"
                )
            );
        usersRepository.save(user);
        // access token 발급, refresh token 발급
        LocalDateTime now = LocalDateTime.now();

        Token refreshToken = jwtProvider.createRefreshToken(user, now, REFRESH_TOKEN_EXPIRATION_TIME);
        Token accessToken = jwtProvider.createAccessToken(user, now, ACCESS_TOKEN_EXPIRATION_TIME);
        
        AuthTokenResponse authTokenResponse = AuthTokenResponse.of(accessToken.getToken(), refreshToken.getToken(), accessToken.getExpiredAt(), refreshToken.getExpiredAt());

        // redis 적용
        String refreshTokenAsString = refreshToken.getToken();
        // 토큰 만료 시간 설정
        redisTemplate.opsForValue().set(refreshTokenAsString, String.valueOf(user.getId()), REFRESH_TOKEN_EXPIRATION_TIME, TimeUnit.MINUTES);
        // 발급된 토큰 반환
        return authTokenResponse;
    }

    public RedirectUrlResponse redirectUrlByGoogle(String redirectUri) {
        return RedirectUrlResponse.googleOf(clientId, redirectUri);
    }

    public AuthTokenResponse refreshToken(String token) {
        // 토큰 유효성 검사
        if (! jwtProvider.validateToken(token)) {
            throw new RuntimeException("Refresh token is invalid");
        }
        // redis 에서 토큰 조회
        String userIdAsString = Optional.ofNullable(redisTemplate.opsForValue().get(token))
            .orElseThrow(() -> new RuntimeException("Refresh token is not found"))
            .toString();
        long userId = Long.parseLong(userIdAsString);
        // 유저 조회
        UserEntity user = usersRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found"));
        // access token 발급, refresh token 발급
        LocalDateTime now = LocalDateTime.now();
        Token accessToken = jwtProvider.createAccessToken(user, now, ACCESS_TOKEN_EXPIRATION_TIME);
        Token refreshToken = jwtProvider.createRefreshToken(user, now, REFRESH_TOKEN_EXPIRATION_TIME);
        
        // 기존 토큰 삭제
        redisTemplate.delete(token);
        // redis 적용
        String refreshTokenAsString = refreshToken.getToken();
        redisTemplate.opsForValue().set(refreshTokenAsString, String.valueOf(user.getId()), REFRESH_TOKEN_EXPIRATION_TIME, TimeUnit.MINUTES);
        // 발급된 토큰 반환
        return AuthTokenResponse.of(accessToken.getToken(), refreshToken.getToken(), accessToken.getExpiredAt(), refreshToken.getExpiredAt());
    }

    public UserInfoResponse getUserInfo(String token) {
        // 토큰 유효성 검사
        if (! jwtProvider.validateToken(token)) {
            throw new RuntimeException("Access token is invalid");
        }

        // access token 값을 파싱하여 userId 값 가져오기
        String userIdAsString = jwtProvider.getSubject(token);
        long userId = Long.parseLong(userIdAsString);
        // 유저 조회
        UserEntity user = usersRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found"));
        // 유저 정보 반환
        return UserInfoResponse.from(user);
    }

    public void getUserInfo(HttpServletRequest request, HttpServletResponse response) {
        String requestURI = request.getRequestURI();
        if(jwtProvider.isWhiteList(requestURI)) {
            return;
        }

        String token = request.getHeader("Authorization");
        final String BEARER = "Bearer ";
        // 토큰 유효성 검사
        if(token == null) {
            throw new RuntimeException("Token is null");
        }
        // bearer 토큰 형식 검사
        if(!token.startsWith(BEARER)) {
            throw new RuntimeException("Token is not Bearer");
        }

        token = token.substring(BEARER.length());
        // 토큰 유효성 검사
        Authentication authentication = jwtProvider.getAuthentication(token);

        response.setHeader("X-User-Id", authentication.getName());
        response.setHeader("X-User-Role", authentication.getAuthorities().toString());
    }
    
}
