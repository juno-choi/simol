package com.simol.simolcommon.auth.vo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TokenTest {
    @Test
    @DisplayName("Token 생성 테스트")
    void ofSuccess() {
        final String TOKEN = "token";
        final Long EXPIRED_AT = 1L;

        Token token = Token.of(TOKEN, EXPIRED_AT);

        Assertions.assertThat(token.getToken()).isEqualTo(TOKEN);
        Assertions.assertThat(token.getExpiredAt()).isEqualTo(EXPIRED_AT);
    }
}
