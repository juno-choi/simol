package com.simol.simoluser.user.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class RedirectUrlResponse {
    @Schema(description = "리다이렉트 URL", example = "https://accounts.google.com/o/oauth2/auth")
    private String url;

    public static RedirectUrlResponse googleOf(String clientId, String redirectUri) {
        String url = "https://accounts.google.com/o/oauth2/auth"
        + "?client_id=" + clientId
        + "&redirect_uri=" + redirectUri
        + "&response_type=token"
        + "&scope=https://www.googleapis.com/auth/userinfo.email https://www.googleapis.com/auth/userinfo.profile";
        return RedirectUrlResponse.builder()
            .url(url)
            .build();
    }
}
