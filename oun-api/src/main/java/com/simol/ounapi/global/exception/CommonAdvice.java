package com.simol.ounapi.global.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.simol.ouncommon.api.ErrorApi;
import com.simol.ouncommon.api.ErrorDto;
import com.simol.ouncommon.exception.BadRequestException;
import com.simol.ouncommon.exception.UnAuthorizedException;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice(basePackages = {"com.simol.ounapi"})
@Slf4j
public class CommonAdvice {
    
    @ExceptionHandler
    public ResponseEntity<ErrorApi> handleException(BadRequestException badRequestException) {
        
        List<ErrorDto> errors = new ArrayList<>();
        errors.add(ErrorDto.of("", badRequestException.getMessage()));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(ErrorApi.of("400", "요청을 다시 확인해주세요.", errors));
    }

    @ExceptionHandler
    public ResponseEntity<ErrorApi> handleException(MethodArgumentNotValidException methodArgumentNotValidException) {
        log.error("handleException: {}", methodArgumentNotValidException.getMessage());
        List<ErrorDto> errors = new ArrayList<>();
        for (FieldError error : methodArgumentNotValidException.getBindingResult().getFieldErrors()) {
            errors.add(ErrorDto.of(error.getField(), error.getDefaultMessage()));
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(ErrorApi.of("0400", "요청을 다시 확인해주세요.", errors));
    }

    @ExceptionHandler
    public ResponseEntity<ErrorApi> handleException(UnAuthorizedException unauthorizedException) {
        List<ErrorDto> errors = new ArrayList<>();
        errors.add(ErrorDto.of("", unauthorizedException.getMessage()));

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
            .body(ErrorApi.of("0401", "인증 실패", errors));
    }
}
