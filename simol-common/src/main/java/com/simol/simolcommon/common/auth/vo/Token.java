package com.simol.simolcommon.common.auth.vo;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Builder
public class Token {
    private String token;
    private Long expiredAt;

    public static Token of(String token, Long expiredAt) {
        return Token.builder()
            .token(token)
            .expiredAt(expiredAt)
            .build();
    }
}
