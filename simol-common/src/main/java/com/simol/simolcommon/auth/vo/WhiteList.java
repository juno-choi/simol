package com.simol.simolcommon.auth.vo;

public class WhiteList {
    public static final String[] LIST = {
        "/favicon.ico", 
        // swagger
        // api
        "/api/oun/swagger/**", "/api/oun/swagger-ui/**", "/api/oun/v3/api-docs/**", "/api/oun/v3/api-docs", "/api/oun/swagger-ui.html", "/api/oun/swagger-ui/index.html",
        // user
        "/user/swagger/**", "/user/swagger-ui/**", "/user/v3/api-docs/**", "/user/v3/api-docs", "/user/swagger-ui.html", "/user/swagger-ui/index.html",
        // h2
        "/h2-console/**",
        // test
        "/api/oun/test", "/api/oun/test/**",
        // actuator
        "/api/oun/actuator/**", "/api/oun/actuator", "/api/oun/actuator/prometheus", "/user/actuator/**", "/user/actuator"
    };
}
