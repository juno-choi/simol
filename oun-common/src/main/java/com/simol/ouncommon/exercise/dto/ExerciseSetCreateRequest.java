package com.simol.ouncommon.exercise.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

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
public class ExerciseSetCreateRequest {
    @NotNull(message = "운동 아이디는 필수 입력 값입니다.")
    @Schema(description = "운동 아이디", example = "1")
    @JsonProperty("exercise_id")
    private Long exerciseId;

    @NotNull(message = "세트 번호는 필수 입력 값입니다.")
    @Schema(description = "세트 번호 (정렬)", example = "1")
    @JsonProperty("number")
    private int number;

    @Schema(description = "세트 개수", example = "20")
    @JsonProperty("count")
    private int count;

    @Schema(description = "세트 무게(kg)", example = "30")
    @JsonProperty("weight")
    private int weight;

    @Schema(description = "세트 거리(m)", example = "0")
    @JsonProperty("distance")
    private int distance;

    @Schema(description = "세트 시간(초)", example = "0")
    @JsonProperty("time")
    private int time;

    @Schema(description = "세트 속도(km/h)", example = "0")
    @JsonProperty("speed")
    private double speed;
    
    @Schema(description = "설명", example = "스쿼트 설명")
    private String description;
    
}
