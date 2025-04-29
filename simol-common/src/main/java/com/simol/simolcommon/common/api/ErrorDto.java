package com.simol.simolcommon.common.api;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorDto {
    @NotNull(message = "point 비어있을 수 없습니다.")
    @Schema(description = "에러 포인트", example = "point")
    private String point;
    @NotNull(message = "detail 비어있을 수 없습니다.")
    @Schema(description = "에러 상세 정보", example = "detail")
    private String detail;

    public static ErrorDto of(String point, String detail) {
        return ErrorDto.builder()
            .point(point)
            .detail(detail)
            .build();
    }
}