package com.simol.simolcommon.oun.exercise.vo;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.simol.simolcommon.oun.exercise.entity.ExerciseSetEntity;
import com.simol.simolcommon.oun.exercise.enums.ExerciseSetStatus;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ExerciseSetResponse {
    @Schema(description = "exerciseSetId", example = "1")
    private Long exerciseSetId;

    @Schema(description = "description", example = "description")
    private String description;

    @Schema(description = "status", example = "ACTIVE")
    private ExerciseSetStatus status;

    @Schema(description = "세트 번호 (정렬)", example = "1")
    private int number;

    @Schema(description = "세트 횟수", example = "5")
    private int count;

    @Schema(description = "세트 무게", example = "100")
    private int weight;

    @Schema(description = "세트 거리", example = "100")
    private int distance;

    @Schema(description = "세트 시간", example = "100")
    private int time;

    @Schema(description = "세트 속도", example = "100")
    private double speed;

    @Schema(description = "생성일", example = "2025-01-01T00:00:00")
    private LocalDateTime createdAt;

    @Schema(description = "수정일", example = "2025-01-01T00:00:00")
    private LocalDateTime updatedAt;

    public static ExerciseSetResponse of(ExerciseSetEntity exerciseSet) {
        return ExerciseSetResponse.builder()
            .exerciseSetId(exerciseSet.getId())
            .description(exerciseSet.getDescription())
            .status(exerciseSet.getStatus())
            .number(exerciseSet.getNumber())
            .count(exerciseSet.getCount())
            .weight(exerciseSet.getWeight())
            .distance(exerciseSet.getDistance())
            .time(exerciseSet.getTime())
            .speed(exerciseSet.getSpeed())
            .createdAt(exerciseSet.getCreatedAt())
            .updatedAt(exerciseSet.getUpdatedAt())
            .build();
    }
}
