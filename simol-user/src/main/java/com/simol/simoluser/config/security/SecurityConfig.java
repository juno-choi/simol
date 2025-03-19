package com.simol.simoluser.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final Environment env;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        if (isLocal()) {
            return http
                .cors(cors -> cors.disable())
                .csrf(AbstractHttpConfigurer::disable)
                .headers(c -> c.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable).disable())   // h2 설정
                .authorizeHttpRequests(auth -> auth
                    .requestMatchers(
                        "/**"
                    ).permitAll()
                    .anyRequest().authenticated()
                )
                .oauth2Login(oauth2 -> oauth2.disable())
                .build();
        }

        return http
            .cors(cors -> cors.disable())
            .csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(
                    "/**"
                ).permitAll()
                .anyRequest().authenticated()
            )
            .oauth2Login(oauth2 -> oauth2.disable())
            .build();
    }

    private boolean isLocal() {
        String[] activeProfiles = env.getActiveProfiles();
        for (String p : activeProfiles) {
            if (p.equals("local")) {
                return true;
            }
        }
        return false;
    }

}
