package com.simol.ounapi.global.config.security;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.simol.ounapi.global.auth.ApiJwtTokenProvider;
import com.simol.ounapi.global.auth.AuthFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class ApiSecurityConfig {

    private final ApiJwtTokenProvider apiJwtTokenProvider;
    private final String[] WHITE_LIST = {"/test", "/test/**", "/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**", "/swagger-resources/**"};
    private final String[] USER_LIST = {"/api/**"};

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
            .cors(cors -> cors.disable())
            .csrf(AbstractHttpConfigurer::disable)
            .headers(c -> c.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable).disable())   // h2 설정
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(USER_LIST).hasRole("USER")
                .requestMatchers(WHITE_LIST).permitAll()
                .requestMatchers(PathRequest.toH2Console()).permitAll()
                .anyRequest().authenticated()
            )
            .addFilterBefore(new AuthFilter(apiJwtTokenProvider), UsernamePasswordAuthenticationFilter.class)
            .build();
    }

}

