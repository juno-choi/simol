package com.simol.ounapi.global.auth;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import com.simol.ouncommon.exception.UnAuthorizedException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class AuthFilter extends OncePerRequestFilter{

    private final ApiJwtTokenProvider apiJwtTokenProvider;

    private final List<String> WHITE_LIST = Arrays.asList(
        "/swagger-ui.html",
        "/swagger-ui/**",
        "/v3/api-docs/**",
        "/swagger-resources/**",
        "/h2-console",
        "/h2-console/**",
        "/test/**",
        "/favicon.ico"
    );

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String requestURI = request.getRequestURI();
        return WHITE_LIST.stream()
        .anyMatch(pattern -> 
            new AntPathMatcher().match(pattern, requestURI));
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        log.info("인증 requestURI: {}", request.getRequestURI());

        // 인증 처리
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);

        final String PREFIX = "Bearer ";

        if (token == null) {
            throw new UnAuthorizedException("token is null.");
        }
        if (!token.startsWith(PREFIX)) {
            throw new UnAuthorizedException("token format is invalid.");
        }

        token = token.substring(PREFIX.length());

        // 테스트 토큰 처리
        if (token.equals("oun-test-token")) {
            request.setAttribute("userId", 1L);
            request.setAttribute("authorities", List.of(new SimpleGrantedAuthority("ROLE_USER")));
            // 정상 처리
            filterChain.doFilter(request, response);    
            return ;
        } 

        Authentication authentication = apiJwtTokenProvider.getAuthentication(token);

        request.setAttribute("userId", authentication.getName());
        request.setAttribute("authorities", authentication.getAuthorities());
        
        // 정상 처리
        filterChain.doFilter(request, response);
    }
}
