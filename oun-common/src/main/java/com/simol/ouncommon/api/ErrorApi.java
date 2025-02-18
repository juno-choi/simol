package com.simol.ouncommon.api;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ErrorApi {
    private String code;
    private String message;
    private List<ErrorDto> errors;

    public static ErrorApi of(String code, String message, List<ErrorDto> errors) {
        return ErrorApi.builder()
            .code(code)
            .message(message)
            .errors(errors)
            .build();
    }
}
