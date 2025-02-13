package com.simol.ounuser.user.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.simol.ouncommon.user.dto.OAuthRequest;
import com.simol.ounuser.config.jwt.JwtProvider;
import com.simol.ounuser.user.vo.RedirectUrlVo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {
    private final JwtProvider jwtProvider;

    @Value("${spring.security.oauth2.client.registration.google.client-id}")
    private String clientId;

    public void authenticateWithGoogle(OAuthRequest oauthRequest) {
        String code = oauthRequest.getCode();
        log.info("code: {}", code);
        // code로 구글 토큰에 확인

        // 구글에서 계정 정보 받아오기
        // 유저 정보 확인 후 저장하기
        // 토큰 발급
    }

    public RedirectUrlVo redirectUrlByGoogle(String redirectUri) {
        return RedirectUrlVo.googleOf(clientId, redirectUri);
    }
    
}
