package com.simol.ounuser.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final MyEntryPoint myEntryPoint;
    private final MyAccessDeniedHandler myAccessDeniedHandler;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
            .csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(
                    "/",
                    "/login",
                    "/swagger-ui.html",
                    "/swagger-ui/**",
                    "/v3/api-docs/**",
                    "/swagger-resources/**"
                ).permitAll()
                .anyRequest().authenticated()
            )
            .oauth2Login(Customizer.withDefaults())
            .build();
    }
    
    // @Bean
    // public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    //     return http.csrf(AbstractHttpConfigurer::disable)
    //             .authorizeRequests(auth -> auth.requestMatchers("/login")
    //             .permitAll().anyRequest().authenticated())
    //             .oauth2Login(Customizer.withDefaults())
    //             .cors(AbstractHttpConfigurer::disable)
    //             .headers(c -> c.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable).disable())   // h2 설정
    //             .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
    //             .exceptionHandling(
    //                     e -> e.authenticationEntryPoint(myEntryPoint)
    //                             .accessDeniedHandler(myAccessDeniedHandler)
    //             ) // 인증 예외처리
    //             .build();
    // }
}
