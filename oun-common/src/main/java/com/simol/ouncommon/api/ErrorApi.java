package com.simol.ouncommon.api;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorApi {
    @Schema(description = "응답 코드", example = "0400")
    private String code;
    @Schema(description = "응답 메시지", example = "Bad Request")
    private String message;
    @Schema(description = "에러 목록")
    private List<ErrorDto> errors;

    public static ErrorApi of(String code, String message, List<ErrorDto> errors) {
        return ErrorApi.builder()
            .code(code)
            .message(message)
            .errors(errors)
            .build();
    }

    public static ErrorApi unauthorized(String message, List<ErrorDto> errors) {
        return ErrorApi.builder()
            .code("0401")
            .message(message)
            .errors(errors)
            .build();
    }

    public static ErrorApi accessDenied(String message, List<ErrorDto> errors) {
        return ErrorApi.builder()
            .code("0403")
            .message(message)
            .errors(errors)
            .build();
    }
}
