package com.simol.ouncommon.routine.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.simol.ouncommon.health.enums.HealthStatus;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RoutineHealthUpdateRequest {
    @Schema(description = "운동 id", example = "1")
    @JsonProperty("health_id")
    private Long healthId;
    
    @Schema(description = "운동 이름", example = "스쿼트")
    @NotNull(message = "운동 이름은 필수 입력 값입니다.")
    private String name;

    @Schema(description = "운동 설명", example = "허리 꽂꽂히 아래에 내려가서는 빨리 올라오기!")
    private String description;

    @Schema(description = "운동 순서", example = "1")
    @NotNull(message = "운동 순서는 필수 입력 값입니다.")
    private int sort;

    @Schema(description = "운동 상태", example = "INACTIVE")
    @NotNull(message = "운동 상태는 필수 입력 값입니다.")
    private HealthStatus status;
}
