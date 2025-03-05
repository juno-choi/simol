package com.simol.ounapi.global.config.swagger;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class ApiSwaggerConfig {
    @Bean
    public OpenAPI openApi() {
        final String USER_ID = "X-User-Id";
        final String USER_ROLE = "X-User-Role";
        
        SecurityScheme userId = new SecurityScheme()
            .type(SecurityScheme.Type.APIKEY)
            .in(SecurityScheme.In.HEADER)
            .name(USER_ID)
            .description("사용자 아이디");

        SecurityScheme userRole = new SecurityScheme()
            .type(SecurityScheme.Type.APIKEY)
            .in(SecurityScheme.In.HEADER)
            .name(USER_ROLE)
            .description("사용자 역할");


        SecurityScheme bearerAuth = new SecurityScheme()
            .type(SecurityScheme.Type.HTTP)
            .scheme("bearer")
            .bearerFormat("JWT")
            .in(SecurityScheme.In.HEADER)
            .name(HttpHeaders.AUTHORIZATION);

        SecurityRequirement requirement = new SecurityRequirement()
            .addList(USER_ID)
            .addList(USER_ROLE)
            .addList(HttpHeaders.AUTHORIZATION);

        return new OpenAPI()
            .components(new Components()
                .addSecuritySchemes(USER_ID, userId)
                .addSecuritySchemes(USER_ROLE, userRole)
                .addSecuritySchemes(HttpHeaders.AUTHORIZATION, bearerAuth))
            .security(List.of(requirement))  // 전역 설정
            .info(new Info()
                .title("OUN API")
                .version("0.0.1")
                .description("OUN API")
            );
    }

    @Bean
    public GroupedOpenApi routineApi() {
        return GroupedOpenApi.builder()
            .group("routine api")
            .pathsToMatch("/api/routine/**")
            .addOpenApiCustomizer(openApi -> {
                @SuppressWarnings("rawtypes")
                Map<String, Schema> schemas = openApi.getComponents().getSchemas();
                openApi.getComponents().setSchemas(new TreeMap<>(schemas));
            })
            .build();
    }

    @Bean
    public GroupedOpenApi totalApi() {
        return GroupedOpenApi.builder()
            .group("total api")
            .pathsToMatch("/**")
            .addOpenApiCustomizer(openApi -> {
                @SuppressWarnings("rawtypes")
                Map<String, Schema> schemas = openApi.getComponents().getSchemas();
                openApi.getComponents().setSchemas(new TreeMap<>(schemas));
            })
            .build();
    }

}
