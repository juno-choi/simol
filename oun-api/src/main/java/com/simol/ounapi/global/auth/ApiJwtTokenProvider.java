package com.simol.ounapi.global.auth;

import java.security.Key;
import java.util.Arrays;
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

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class ApiJwtTokenProvider {
    @Value("${jwt.secret-key}")
    private String secretKey;

    private final UsersRepository usersRepository;


    public Authentication getAuthentication(String token) {
        // test token이 아니면 패스
        if(isTestProccess(token)) {
            log.info("test access");
            return createTestAuthentication();
        }

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
