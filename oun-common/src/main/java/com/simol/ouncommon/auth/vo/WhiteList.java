package com.simol.ouncommon.auth.vo;

public class WhiteList {
    public static final String[] LIST = {
        "/favicon.ico", 
        // swagger
        // api
        "/api/swagger/**", "/api/swagger-ui/**", "/api/v3/api-docs/**", "/api/v3/api-docs", "/api/swagger-ui.html", "/api/swagger-ui/index.html",
        // user
        "/user/swagger/**", "/user/swagger-ui/**", "/user/v3/api-docs/**", "/user/v3/api-docs", "/user/swagger-ui.html", "/user/swagger-ui/index.html",
        // h2
        "/h2-console/**",
        // test
        "/api/test", "/api/test/**",
        // actuator
        "/api/actuator/**", "/api/actuator", "/api/actuator/prometheus", "/user/actuator/**", "/user/actuator"
    };
}
