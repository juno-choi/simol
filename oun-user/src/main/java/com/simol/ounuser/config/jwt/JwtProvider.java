package com.simol.ounuser.config.jwt;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.simol.ouncommon.user.entity.UserEntity;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtProvider {

    @Value("${jwt.secret-key}")
    private String secretKey;
    private final long EXPIRATION_TIME = 1000 * 60 * 60; // 1시간

    public String createToken(UserEntity userEntity) {
        return Jwts.builder()
        .setSubject(userEntity.getEmail())
        .claim("role", userEntity.getRole())
        .setIssuedAt(new Date())
        .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
        .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()), SignatureAlgorithm.HS256)
        .compact();
    }
}
