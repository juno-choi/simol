package com.simol.ounuser.user.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClient;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.simol.ouncommon.user.entity.UserEntity;
import com.simol.ouncommon.user.repository.UsersRepository;
import com.simol.ounuser.config.jwt.JwtProvider;
import com.simol.ounuser.user.vo.GoogleUserInfoResponse;
import com.simol.ounuser.user.vo.RedirectUrlResponse;

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

    @Value("${spring.security.oauth2.client.registration.google.client-id}")
    private String clientId;

    @Value("${spring.security.oauth2.client.registration.google.client-secret}")
    private String clientSecret;

    @Transactional
    public void authenticateWithGoogle(String accessToken) {
        log.debug("accessToken: {}", accessToken);
        final String GOOGLE_USER_INFO_URL = "https://www.googleapis.com/oauth2/v1/userinfo?access_token=%s".formatted(accessToken);
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
        String token = jwtProvider.createToken(user);
        log.info("token: {}", token);
        // redis 적용

        // 발급된 토큰 반환
    }

    public RedirectUrlResponse redirectUrlByGoogle(String redirectUri) {
        return RedirectUrlResponse.googleOf(clientId, redirectUri);
    }
    
}
