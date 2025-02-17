package com.simol.ounuser.config.jwt;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.simol.ouncommon.auth.entity.UserEntity;
import com.simol.ouncommon.auth.vo.Token;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwtProvider {

    @Value("${jwt.secret-key}")
    private String secretKey;

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
}
