package com.simol.ounapi.global.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Component
@Aspect
@Slf4j
public class ControllerAspect {
    
    @Before("execution(* com.simol.ounapi..controller..*.*(..))")
    public void before(JoinPoint joinPoint) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
            .currentRequestAttributes()).getRequest();
        
        String type = joinPoint.getSignature().getDeclaringTypeName();
        String method = joinPoint.getSignature().getName();
        String requestURI = request.getRequestURI();

        log.info("[oun-api] call controller... requestUri=[{}] package = [{}], method = [{}]",
            requestURI, type, method);
    }    
}
