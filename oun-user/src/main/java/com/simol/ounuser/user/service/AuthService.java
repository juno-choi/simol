package com.simol.ounuser.user.service;

import org.springframework.stereotype.Service;

import com.simol.ouncommon.user.dto.OAuthRequest;
import com.simol.ounuser.config.jwt.JwtProvider;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final JwtProvider jwtProvider;

    public void authenticateWithGoogle(OAuthRequest oauthRequest) {
        // todo 로그인 처리 필요
    }
    
}
