package com.simol.ounuser.user.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class RedirectUrlVo {
    @Schema(description = "리다이렉트 URL", example = "https://accounts.google.com/o/oauth2/auth")
    private String url;

    public static RedirectUrlVo googleOf(String clientId, String redirectUri) {
        String url = "https://accounts.google.com/o/oauth2/auth"
        + "?client_id=" + clientId
        + "&redirect_uri=" + redirectUri
        + "&response_type=code"
        + "&scope=email profile";
        return RedirectUrlVo.builder()
            .url(url)
            .build();
    }
}
