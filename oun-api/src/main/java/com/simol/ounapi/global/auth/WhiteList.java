package com.simol.ounapi.global.auth;

public class WhiteList {
    public static final String[] LIST = {
        "/favicon.ico", 
        "/api/swagger/**", "/api/swagger-ui/**", "/api/v3/api-docs/**", "/api/v3/api-docs", "/api/swagger-ui.html", "/api/swagger-ui/index.html",
        "/api/test", "/api/test/**"
    };
}
