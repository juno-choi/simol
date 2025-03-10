package com.simol.ouncommon.routine.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@Schema(description = "루틴 생성 요청")
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RoutineCreateRequest {
    @Schema(description = "루틴 이름", example = "월요일 루틴")
    @NotNull(message = "루틴 이름은 필수 입력 값입니다.")
    private String name;

    @Schema(description = "루틴 설명", example = "하체 부시는 날")
    private String description;

    @Schema(description = "루틴 정렬", example = "1")
    @NotNull(message = "루틴 정렬은 필수 입력 값입니다.")
    private int sort;
}
