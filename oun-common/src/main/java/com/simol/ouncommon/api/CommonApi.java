package com.simol.ouncommon.api;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommonApi<T> {
    @Schema(description = "응답 코드", example = "0000")
    private String code;
    @Schema(description = "응답 메시지", example = "success")
    private String message;
    @Schema(description = "응답 데이터")
    private T data;

    public static <T> CommonApi<T> of(String code, String message, T data) {
        return CommonApi.<T>builder()
            .code(code)
            .message(message)
            .data(data)
            .build();
    }
    
    public static <T> CommonApi<T> create(T data) {
        return CommonApi.<T>builder()
            .code("0001")
            .message("created")
            .data(data)
            .build();
    }

    public static <T> CommonApi<T> success(T data) {
        return CommonApi.<T>builder()
            .code("0000")
            .message("success")
            .data(data)
            .build();
    }
    
}

