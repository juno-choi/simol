package com.simol.ouncommon.healthset.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.simol.ouncommon.healthset.enums.HealthSetType;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class HealthSetCreateRequest {
    @NotNull(message = "운동 아이디는 필수 입력 값입니다.")
    @Schema(description = "운동 아이디", example = "1")
    @JsonProperty("health_id")
    private Long healthId;

    @NotNull(message = "운동 타입은 필수 입력 값입니다.")
    @Schema(description = "운동 타입", example = "WEIGHT")
    @JsonProperty("health_set_type")
    private HealthSetType healthSetType;

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
