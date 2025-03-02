package com.simol.ounapi.global.auth;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
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

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String requestURI = request.getRequestURI();
        return Arrays.stream(WhiteList.LIST)
        .anyMatch(pattern -> 
            new AntPathMatcher().match(pattern, requestURI));
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        log.info("인증 requestURI: {}", request.getRequestURI());

        // Traefik에서 전달한 사용자 정보 헤더 읽기
        
        Authentication authentication = apiJwtTokenProvider.getAuthentication(request);
        // SecurityContext에 인증 객체 설정
        SecurityContextHolder.getContext().setAuthentication(authentication);

        request.setAttribute("userId", authentication.getName());
        request.setAttribute("userRole", authentication.getAuthorities());
        
        // 정상 처리
        filterChain.doFilter(request, response);
    }
}
