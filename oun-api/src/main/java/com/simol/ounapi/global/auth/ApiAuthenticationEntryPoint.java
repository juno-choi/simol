package com.simol.ounapi.global.auth;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.simol.simolcommon.common.api.ErrorApi;
import com.simol.simolcommon.common.api.ErrorDto;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
public class ApiAuthenticationEntryPoint implements AuthenticationEntryPoint{
    private final ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException authException) throws IOException, ServletException {
        log.error("authentication error: {}", authException.getMessage());

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        ErrorDto errorDto = ErrorDto.of("token", "please check your token. token is start with 'Bearer '");
        List<ErrorDto> errors = Arrays.asList(errorDto);
        ErrorApi errorApi = ErrorApi.unauthorized("unauthorized", errors);

        PrintWriter writer = response.getWriter();
        writer.write(objectMapper.writeValueAsString(errorApi));
        writer.flush();
    }
    
}
