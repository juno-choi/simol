package com.simol.ounapi.global.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.simol.ouncommon.api.ErrorApi;
import com.simol.ouncommon.api.ErrorDto;
import com.simol.ouncommon.exception.BadRequestException;

@RestControllerAdvice(basePackages = {"com.simol.ounapi"})
public class CommonAdvice {
    
    @ExceptionHandler
    public ResponseEntity<ErrorApi> handleException(BadRequestException badRequestException) {
        
        List<ErrorDto> errors = new ArrayList<>();
        errors.add(ErrorDto.of("", badRequestException.getMessage()));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(ErrorApi.of("400", "요청을 다시 확인해주세요.", errors));
    }
}
