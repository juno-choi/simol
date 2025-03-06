package com.simol.ounapi.global.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.simol.ouncommon.api.ErrorApi;
import com.simol.ouncommon.api.ErrorDto;
import com.simol.ouncommon.exception.BadRequestException;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorApi> handleBadRequestException(BadRequestException e) {
        log.error("BadRequestException: {}", e.getMessage());
        ErrorDto errorDto = ErrorDto.of("error", e.getMessage());
        List<ErrorDto> errors = List.of(errorDto);
        ErrorApi errorApi = ErrorApi.badRequest("Bad Request", errors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorApi);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorApi> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("MethodArgumentNotValidException: {}", e.getMessage());
        BindingResult bindingResult = e.getBindingResult();
        List<ErrorDto> errors = new ArrayList<>();
        
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            errors.add(ErrorDto.of(fieldError.getField(), fieldError.getDefaultMessage()));
        }
        
        ErrorApi errorApi = ErrorApi.badRequest("Validation Error", errors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorApi);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ErrorApi> handleNullPointerException(NullPointerException e) {
        log.error("NullPointerException: {}", e.getMessage());
        ErrorDto errorDto = ErrorDto.of("error", "필수 값이 누락되었습니다. 요청을 확인해주세요.");
        List<ErrorDto> errors = List.of(errorDto);
        ErrorApi errorApi = ErrorApi.badRequest("Bad Request", errors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorApi);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorApi> handleException(Exception e) {
        log.error("Exception: {}", e.getMessage(), e);
        ErrorDto errorDto = ErrorDto.of("error", "서버 오류가 발생했습니다.");
        List<ErrorDto> errors = List.of(errorDto);
        ErrorApi errorApi = ErrorApi.internalServerError("Internal Server Error", errors);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorApi);
    }
} 