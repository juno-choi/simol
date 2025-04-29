package com.simol.simolcommon.common.api;

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

    public static ErrorApi badRequest(String message, List<ErrorDto> errors) {
        return ErrorApi.builder()
            .code(ErrorCode.BAD_REQUEST.code)
            .message(message)
            .errors(errors)
            .build();
    }

    public static ErrorApi unauthorized(String message, List<ErrorDto> errors) {
        return ErrorApi.builder()
            .code(ErrorCode.UNAUTHORIZED.code)
            .message(message)
            .errors(errors)
            .build();
    }

    public static ErrorApi accessDenied(String message, List<ErrorDto> errors) {
        return ErrorApi.builder()
            .code(ErrorCode.FORBIDDEN.code)
            .message(message)
            .errors(errors)
            .build();
    }

    public static ErrorApi internalServerError(String message, List<ErrorDto> errors) {
        return ErrorApi.builder()
            .code(ErrorCode.INTERNAL_SERVER_ERROR.code)
            .message(message)
            .errors(errors)
            .build();
    }
}
