package com.simol.simolcommon.oun.exercise.vo;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.simol.simolcommon.oun.exercise.entity.ExerciseSetEntity;
import com.simol.simolcommon.oun.exercise.enums.ExerciseSetStatus;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ExerciseSetCreateResponse {
    @Schema(description = "healthSetId", example = "1")
    private Long id;

    @Schema(description = "description", example = "description")
    private String description;

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

    @Schema(description = "상태", example = "ACTIVE")
    private ExerciseSetStatus status;

    @Schema(description = "생성일", example = "2025-01-01T00:00:00")
    private LocalDateTime createdAt;

    @Schema(description = "수정일", example = "2025-01-01T00:00:00")
    private LocalDateTime updatedAt;

    public static ExerciseSetCreateResponse of(ExerciseSetEntity exerciseSetEntity) {
        return ExerciseSetCreateResponse.builder()
            .id(exerciseSetEntity.getId())
            .description(exerciseSetEntity.getDescription())
            .number(exerciseSetEntity.getNumber())
            .count(exerciseSetEntity.getCount())
            .weight(exerciseSetEntity.getWeight())
            .distance(exerciseSetEntity.getDistance())
            .time(exerciseSetEntity.getTime())
            .speed(exerciseSetEntity.getSpeed())
            .status(exerciseSetEntity.getStatus())
            .build();
    }   
}
