package com.simol.ounuser.user.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class GoogleTokenResponse {
    @JsonProperty("access_token")
    private String accessToken;
}
