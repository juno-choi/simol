package com.simol.ouncommon.health.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class HealthHealthSetUpdateRequest {
    @Schema(description = "운동 세트 아이디", example = "1")
    @NotNull(message = "운동 세트 아이디는 필수 입력 값입니다.")
    @JsonProperty("health_set_id")
    private Long healthSetId;

    @NotNull(message = "세트 번호는 필수 입력 값입니다.")
    @Schema(description = "세트 번호 (정렬)", example = "1")
    @JsonProperty("set_number")
    private int setNumber;

    @Schema(description = "세트 개수", example = "20")
    @JsonProperty("set_count")
    private int setCount;

    @Schema(description = "세트 무게(kg)", example = "30")
    @JsonProperty("set_weight")
    private int setWeight;

    @Schema(description = "세트 거리(m)", example = "0")
    @JsonProperty("set_distance")
    private int setDistance;

    @Schema(description = "세트 시간(초)", example = "0")
    @JsonProperty("set_time")
    private int setTime;

    @Schema(description = "세트 속도(km/h)", example = "0")
    @JsonProperty("set_speed")
    private int setSpeed;
    
    @Schema(description = "설명", example = "스쿼트 설명")
    private String description;
}
