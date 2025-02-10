package com.simol.ounuser.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openApi() {
        return new OpenAPI()
            .info(new Info()
                .title("OUN User API")
                .version("0.0.1")
                .description("OUN User API"));
    }
}
