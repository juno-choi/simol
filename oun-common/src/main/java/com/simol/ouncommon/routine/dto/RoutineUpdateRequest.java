package com.simol.ouncommon.routine.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.simol.ouncommon.routine.enums.RoutineStatus;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Schema(description = "루틴 생성 요청")
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RoutineUpdateRequest {
    @Schema(description = "루틴 아이디", example = "1")
    private long routineId;

    @Schema(description = "루틴 이름", example = "월요일 루틴")
    private String name;

    @Schema(description = "루틴 설명", example = "하체 부시는 날")
    private String description;

    @Schema(description = "루틴 상태(활성화 : ACTIVE, 비활성화 : INACTIVE)", example = "ACTIVE")
    private RoutineStatus status;
}
