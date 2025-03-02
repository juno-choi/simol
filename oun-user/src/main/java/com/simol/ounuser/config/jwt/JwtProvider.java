package com.simol.ounuser.config.jwt;

import java.security.Key;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.simol.ouncommon.auth.entity.UserEntity;
import com.simol.ouncommon.auth.repository.UsersRepository;
import com.simol.ouncommon.auth.vo.Token;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
public class JwtProvider {

    @Value("${jwt.secret-key}")
    private String secretKey;

    private final UsersRepository usersRepository;

    public Token createRefreshToken(UserEntity userEntity, LocalDateTime now, Long plusMinutes) {
        LocalDateTime expirationDateTime = now.plusMinutes(plusMinutes);
        Instant instant = expirationDateTime.atZone(ZoneId.systemDefault()).toInstant();
        Date expiration = Date.from(instant);

        String token = Jwts.builder()
            .setSubject(String.valueOf(userEntity.getId()))
            .claim("role", userEntity.getRole())
            .setIssuedAt(new Date())
            .setExpiration(expiration)
            .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()), SignatureAlgorithm.HS256)
            .compact();

        return Token.of(token, expiration.getTime());
    }

    public Token createAccessToken(UserEntity userEntity, LocalDateTime now, long plusMinutes) {
        LocalDateTime expirationDateTime = now.plusMinutes(plusMinutes);
        Instant instant = expirationDateTime.atZone(ZoneId.systemDefault()).toInstant();
        Date expiration = Date.from(instant);

        String token = Jwts.builder()
            .setSubject(String.valueOf(userEntity.getId()))
            .claim("role", userEntity.getRole())
            .setIssuedAt(new Date())
            .setExpiration(expiration)
            .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()), SignatureAlgorithm.HS256)
            .compact();

        return Token.of(token, expiration.getTime());
    }

    public boolean validateToken(String refreshToken) {
        // 토큰 유효성 검사
        try {
            Jws<Claims> claimsJws = Jwts.parserBuilder()
                .setSigningKey(secretKey.getBytes()).build()
                .parseClaimsJws(refreshToken);
            // 토큰 만료 시간 검사
            if (claimsJws.getBody().getExpiration().before(new Date())) {
                return false;
            }
            // 토큰 유효성 검사 성공
            return true;
        } catch (Exception e) {
            // 토큰 유효성 검사 실패
            log.error("refresh token 유효성 검사 실패", e);
            return false;
        }
    }

    public String getSubject(String token) {
        return Jwts.parserBuilder()
            .setSigningKey(secretKey.getBytes()).build()
            .parseClaimsJws(token)
            .getBody()
            .getSubject();
    }

    public Authentication getAuthentication(String token) {
        // test token이 아니면 패스
        if(isTestProccess(token)) {
            log.info("test access");
            return createTestAuthentication();
        }

        // key 생성
        Key key = Keys.hmacShaKeyFor(secretKey.getBytes());

        // parseClaims
        Claims claims = null;
        try {
            JwtParser jwtParser = Jwts.parserBuilder().setSigningKey(key).build();
            claims = jwtParser.parseClaimsJws(token).getBody();
        } catch (MalformedJwtException me) {
            // invalid token
        } catch (ExpiredJwtException ee) {
            // token expired
            claims = ee.getClaims();
        }
        
        // claims authentication 가져오기
        String claimsBody = Optional.ofNullable(claims.get("role")).orElse("").toString();
        log.info("claimsBody: {}", claimsBody);

        List<GrantedAuthority> authorities = Arrays.stream(claimsBody.split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        return new UsernamePasswordAuthenticationToken(claims.getSubject(), "", authorities);
    }

    private boolean isTestProccess(String token) {
        final String TEST_TOKEN = "oun-test-token";
        return token.equals(TEST_TOKEN);
    }

    private UsernamePasswordAuthenticationToken createTestAuthentication() {
        final String TEST_EMAIL = "juno@simol.com";
        final String TEST_NAME = "테스터";
        final String TEST_PROFILE_IMAGE = "";
        final String TEST_ROLE = "ROLE_USER";
        UserEntity userEntity = usersRepository.findByEmail(TEST_EMAIL)
            .orElseGet(() -> UserEntity.create(TEST_EMAIL, TEST_NAME, TEST_PROFILE_IMAGE, TEST_ROLE));
        UserEntity saveUser = usersRepository.save(userEntity);
        return new UsernamePasswordAuthenticationToken(saveUser.getId(), "", List.of(new SimpleGrantedAuthority(TEST_ROLE)));
    }
}
