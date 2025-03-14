package com.simol.ouncommon.healthset.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.simol.ouncommon.healthset.enums.HealthSetStatus;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HealthSetUpdateRequest {
    @JsonProperty("health_set_id")
    @Schema(description = "healthSetId", example = "1")
    private long healthSetId;

    @Schema(description = "description", example = "세트에 대한 설명")
    private String description;

    @JsonProperty("set_number")
    @Schema(description = "세트 번호 (정렬)", example = "1")
    private int setNumber;

    @JsonProperty("set_count")
    @Schema(description = "세트 횟수", example = "5")
    private int setCount;

    @JsonProperty("set_weight")
    @Schema(description = "세트 무게", example = "0")
    private int setWeight;

    @JsonProperty("set_distance")
    @Schema(description = "세트 거리", example = "500")
    private int setDistance;

    @JsonProperty("set_time")
    @Schema(description = "세트 시간", example = "180")
    private int setTime;

    @JsonProperty("set_speed")
    @Schema(description = "세트 속도", example = "9")
    private double setSpeed;

    @JsonProperty("status")
    @Schema(description = "상태", example = "ACTIVE")
    private HealthSetStatus status;
}
