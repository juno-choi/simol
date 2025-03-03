package com.simol.ouncommon.auth.vo;

public class WhiteList {
    public static final String[] LIST = {
        "/favicon.ico", 
        // swagger
        "/api/swagger/**", "/api/swagger-ui/**", "/api/v3/api-docs/**", "/api/v3/api-docs", "/api/swagger-ui.html", "/api/swagger-ui/index.html",
        // user
        "/user/auth",
        // h2
        "/h2-console/**",
        // test
        "/api/test", "/api/test/**"
    };
}
