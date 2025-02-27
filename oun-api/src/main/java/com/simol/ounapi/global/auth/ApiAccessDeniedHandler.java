package com.simol.ounapi.global.auth;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.simol.ouncommon.api.ErrorApi;
import com.simol.ouncommon.api.ErrorDto;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
public class ApiAccessDeniedHandler implements AccessDeniedHandler{
    private final ObjectMapper objectMapper;

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
            AccessDeniedException accessDeniedException) throws IOException, ServletException {
        log.error("access denied: {}", accessDeniedException.getMessage());

        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        ErrorDto errorDto = ErrorDto.of("access", "access denied");
        List<ErrorDto> errors = Arrays.asList(errorDto);
        ErrorApi errorApi = ErrorApi.accessDenied("access denied", errors);

        PrintWriter writer = response.getWriter();
        writer.write(objectMapper.writeValueAsString(errorApi));
        writer.flush();
    }
    
}
