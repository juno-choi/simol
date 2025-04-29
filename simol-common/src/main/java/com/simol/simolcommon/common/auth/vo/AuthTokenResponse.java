package com.simol.simolcommon.common.auth.vo;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Builder
public class AuthTokenResponse {
    private String accessToken;
    private String refreshToken;
    private Long accessTokenExpiredAt;
    private Long refreshTokenExpiredAt;

    public static AuthTokenResponse of(String accessToken, String refreshToken, Long accessTokenExpiredAt, Long refreshTokenExpiredAt) {
        return AuthTokenResponse.builder()
            .accessToken(accessToken)
            .refreshToken(refreshToken)
            .accessTokenExpiredAt(accessTokenExpiredAt)
            .refreshTokenExpiredAt(refreshTokenExpiredAt)
            .build();
    }
}
