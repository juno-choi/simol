package com.simol.simolcommon.oun.exercise.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.simol.simolcommon.oun.exercise.enums.ExerciseSetStatus;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ExerciseSetUpdateRequest {
    @JsonProperty("exercise_set_id")
    @Schema(description = "exerciseSetId", example = "1")
    private long exerciseSetId;

    @Schema(description = "description", example = "세트에 대한 설명")
    private String description;

    @JsonProperty("number")
    @Schema(description = "세트 번호 (정렬)", example = "1")
    private int number;

    @JsonProperty("count")
    @Schema(description = "세트 횟수", example = "5")
    private int count;

    @JsonProperty("weight")
    @Schema(description = "세트 무게", example = "0")
    private int weight;

    @JsonProperty("distance")
    @Schema(description = "세트 거리", example = "500")
    private int distance;

    @JsonProperty("time")
    @Schema(description = "세트 시간", example = "180")
    private int time;

    @JsonProperty("speed")
    @Schema(description = "세트 속도", example = "9")
    private double speed;

    @JsonProperty("status")
    @Schema(description = "상태", example = "ACTIVE")
    private ExerciseSetStatus status;
}
