package com.simol.ounapi.global.config.security;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.simol.ounapi.global.auth.ApiAccessDeniedHandler;
import com.simol.ounapi.global.auth.ApiAuthenticationEntryPoint;
import com.simol.ounapi.global.auth.ApiJwtTokenProvider;
import com.simol.ounapi.global.auth.AuthFilter;
import com.simol.ouncommon.auth.vo.WhiteList;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class ApiSecurityConfig {

    private final ApiJwtTokenProvider apiJwtTokenProvider;
    private final ApiAuthenticationEntryPoint apiAuthenticationEntryPoint;
    private final ApiAccessDeniedHandler apiAccessDeniedHandler;

    private final Environment env;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        if (isLocal()) {
            return http
                .cors(cors -> cors.disable())
                .csrf(AbstractHttpConfigurer::disable)
                .headers(c -> c.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable).disable())   // h2 설정
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(WhiteList.LIST).permitAll()
                .requestMatchers(PathRequest.toH2Console()).permitAll()
                .anyRequest().authenticated()
            )
            .addFilterBefore(new AuthFilter(apiJwtTokenProvider), UsernamePasswordAuthenticationFilter.class)
            .exceptionHandling(exception -> exception
                .authenticationEntryPoint(apiAuthenticationEntryPoint)
                .accessDeniedHandler(apiAccessDeniedHandler)
            )
            .build();
        }

        return http
                .cors(cors -> cors.disable())
                .csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(WhiteList.LIST).permitAll()
                .anyRequest().authenticated()
            )
            .addFilterBefore(new AuthFilter(apiJwtTokenProvider), UsernamePasswordAuthenticationFilter.class)
            .exceptionHandling(exception -> exception
                .authenticationEntryPoint(apiAuthenticationEntryPoint)
                .accessDeniedHandler(apiAccessDeniedHandler)
            )
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

