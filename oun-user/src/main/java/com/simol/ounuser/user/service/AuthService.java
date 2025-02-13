package com.simol.ounuser.user.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.simol.ounuser.config.jwt.JwtProvider;
import com.simol.ounuser.user.vo.RedirectUrlResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {
    private final JwtProvider jwtProvider;
    private final RestClient restClient;

    @Value("${spring.security.oauth2.client.registration.google.client-id}")
    private String clientId;

    @Value("${spring.security.oauth2.client.registration.google.client-secret}")
    private String clientSecret;


    public void authenticateWithGoogle(String accessToken) {
        log.info("accessToken: {}", accessToken);
        final String GOOGLE_USER_INFO_URL = "https://www.googleapis.com/oauth2/v1/userinfo?access_token=%s".formatted(accessToken);
        // code로 구글 토큰에 확인

        String googleTokenResponse = restClient.get().uri(GOOGLE_USER_INFO_URL)
            .retrieve()
            .body(String.class);


        // 구글에서 계정 정보 받아오기
        log.info("googleTokenResponse: {}", googleTokenResponse);
        // 유저 정보 확인 후 저장하기
        // 토큰 발급
    }

    public RedirectUrlResponse redirectUrlByGoogle(String redirectUri) {
        return RedirectUrlResponse.googleOf(clientId, redirectUri);
    }
    
}
