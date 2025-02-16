package com.simol.ounuser.config.jwt;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.simol.ouncommon.auth.vo.Token;
import com.simol.ouncommon.user.entity.UserEntity;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtProvider {

    @Value("${jwt.secret-key}")
    private String secretKey;

    public Token createRefreshToken(UserEntity userEntity, LocalDateTime now, Long plusMinutes) {
        LocalDateTime expirationDateTime = now.plusMinutes(plusMinutes);
        Instant instant = expirationDateTime.atZone(ZoneId.systemDefault()).toInstant();
        Date expiration = Date.from(instant);

        String token = Jwts.builder()
            .setSubject(userEntity.getEmail())
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
            .setSubject(userEntity.getEmail())
            .setIssuedAt(new Date())
            .setExpiration(expiration)
            .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()), SignatureAlgorithm.HS256)
            .compact();

        return Token.of(token, expiration.getTime());
    }
}
