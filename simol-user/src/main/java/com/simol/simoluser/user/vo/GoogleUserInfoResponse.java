package com.simol.simoluser.user.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class GoogleUserInfoResponse {
    private String id;  // 구글 유저 고유 아이디
    private String email;   // 이메일
    @JsonProperty("verified_email")
    private boolean verifiedEmail;  // 메일 인증 여부
    private String name;    //full name
    @JsonProperty("given_name")
    private String givenName;
    @JsonProperty("family_name")
    private String familyName;
    private String picture;
}
