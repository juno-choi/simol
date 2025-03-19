package com.simol.simolcommon.auth.vo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AuthTokenResponseTest {
    @Test
    @DisplayName("AuthTokenResponse 생성 테스트")
    void ofSuccess() {
        final String ACCESS_TOKEN = "accessToken";
        final String REFRESH_TOKEN = "refreshToken";
        final Long ACCESS_TOKEN_EXPIRED_AT = 1L;
        final Long REFRESH_TOKEN_EXPIRED_AT = 2L;

        AuthTokenResponse authTokenResponse = AuthTokenResponse.of(ACCESS_TOKEN, REFRESH_TOKEN, ACCESS_TOKEN_EXPIRED_AT, REFRESH_TOKEN_EXPIRED_AT);

        Assertions.assertThat(authTokenResponse.getAccessToken()).isEqualTo(ACCESS_TOKEN);
        Assertions.assertThat(authTokenResponse.getRefreshToken()).isEqualTo(REFRESH_TOKEN);
        Assertions.assertThat(authTokenResponse.getAccessTokenExpiredAt()).isEqualTo(ACCESS_TOKEN_EXPIRED_AT);
        Assertions.assertThat(authTokenResponse.getRefreshTokenExpiredAt()).isEqualTo(REFRESH_TOKEN_EXPIRED_AT);
    }
}
