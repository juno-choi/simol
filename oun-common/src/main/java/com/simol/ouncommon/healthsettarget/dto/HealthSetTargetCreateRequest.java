package com.simol.ouncommon.healthsettarget.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

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
public class HealthSetTargetCreateRequest {
    @NotNull(message = "운동 세트 아이디는 필수 입력 값입니다.")
    @Schema(description = "운동 세트 아이디", example = "1")
    @JsonProperty("health_set_id")
    private Long healthSetId;

    @Schema(description = "목표 횟수", example = "10")
    private int number;

    @Schema(description = "목표 무게(kg)", example = "10")
    private double weight;

    @Schema(description = "목표 거리(km)", example = "10")
    private double distance;

    @Schema(description = "목표 시간(초)", example = "10")
    private int time;

    @Schema(description = "목표 속도(km/h)", example = "10")
    private double speed;
    
}
