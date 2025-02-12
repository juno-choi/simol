package com.simol.ounuser.user.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.simol.ouncommon.user.dto.OAuthRequest;
import com.simol.ounuser.config.jwt.JwtProvider;
import com.simol.ounuser.user.vo.RedirectUrlVo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final JwtProvider jwtProvider;

    @Value("${spring.security.oauth2.client.registration.google.client-id}")
    private String clientId;

    public void authenticateWithGoogle(OAuthRequest oauthRequest) {
        // todo 로그인 처리 필요
    }

    public RedirectUrlVo redirectUrlByGoogle(String redirectUri) {
        return RedirectUrlVo.googleOf(clientId, redirectUri);
    }
    
}
