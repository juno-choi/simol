package com.simol.ounapi.global.auth;

import java.security.Key;
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

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class ApiJwtTokenProvider {
    @Value("${jwt.secret-key}")
    private String secretKey;


    public Authentication getAuthentication(String token) {
        // key 생성
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        Key key = Keys.hmacShaKeyFor(keyBytes);

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
        String claimsBody = Optional.ofNullable(claims.get("auth")).orElse("").toString();
        List<GrantedAuthority> authorities = Arrays.stream(claimsBody.split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        return new UsernamePasswordAuthenticationToken(claims.getSubject(), "", authorities);
    }
    
}
