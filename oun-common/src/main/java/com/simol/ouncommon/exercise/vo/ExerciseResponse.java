package com.simol.ouncommon.exercise.vo;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.simol.ouncommon.exercise.entity.ExerciseEntity;
import com.simol.ouncommon.exercise.enums.ExerciseStatus;
import com.simol.ouncommon.exercise.enums.ExerciseType;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Getter
@Builder
public class ExerciseResponse {
    @Schema(description = "운동 아이디", example = "1")
    private Long exerciseId;
    @Schema(description = "운동 이름", example = "푸시업")
    private String name;
    @Schema(description = "운동 설명", example = "푸시업 설명")
    private String description;
    @Schema(description = "운동 순서", example = "1")
    private int sort;
    @Schema(description = "운동 상태", example = "ACTIVE")
    private ExerciseStatus status;
    @Schema(description = "운동 타입", example = "WEIGHT")
    private ExerciseType type;

    @Schema(description = "운동 생성일", example = "2025-01-01 00:00:00")
    private LocalDateTime createdAt;
    @Schema(description = "운동 수정일", example = "2025-01-01 00:00:00")
    private LocalDateTime updatedAt;

    @Schema(description = "운동 세트 목록", example = "[]")
    private List<ExerciseSetResponse> exerciseSetList;

    public static ExerciseResponse of(ExerciseEntity exercise) {
        return ExerciseResponse.builder()
            .exerciseId(exercise.getId())
            .name(exercise.getName())
            .description(exercise.getDescription())
            .sort(exercise.getSort())
            .status(exercise.getStatus())
            .type(exercise.getType())
            .exerciseSetList(exercise.getExerciseSetList().stream().map(ExerciseSetResponse::of).toList())
            .createdAt(exercise.getCreatedAt())
            .updatedAt(exercise.getUpdatedAt())
            .build();
    }
}
