package com.simol.simoluser.config.swagger;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openApi() {
        SecurityScheme bearerAuth = new SecurityScheme()
            .type(SecurityScheme.Type.HTTP)
            .scheme("bearer")
            .bearerFormat("JWT")
            .in(SecurityScheme.In.HEADER)
            .name(HttpHeaders.AUTHORIZATION);

        SecurityRequirement requirement = new SecurityRequirement().addList(HttpHeaders.AUTHORIZATION); // 이름 일치 필요

        return new OpenAPI()
            .components(new Components().addSecuritySchemes(HttpHeaders.AUTHORIZATION, bearerAuth))
            .security(List.of(requirement))  // 전역 설정
            .info(new Info()
                .title("OUN User API")
                .version("0.0.1")
                .description("OUN User API"));
    }
}
