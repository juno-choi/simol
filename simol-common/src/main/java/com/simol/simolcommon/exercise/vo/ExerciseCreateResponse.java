package com.simol.simolcommon.exercise.vo;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.simol.simolcommon.exercise.entity.ExerciseEntity;
import com.simol.simolcommon.exercise.enums.ExerciseStatus;
import com.simol.simolcommon.exercise.enums.ExerciseType;

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
public class ExerciseCreateResponse {
    @Schema(description = "exercise id", example = "1")
    private Long exerciseId;

    @Schema(description = "건강 이름", example = "스쿼트")
    private String name;

    @Schema(description = "설명", example = "하체 부시기")
    private String description;

    @Schema(description = "순서", example = "1")
    private int sort;

    @Schema(description = "상태", example = "활성")
    private ExerciseStatus status;

    @Schema(description = "운동 타입", example = "WEIGHT")
    private ExerciseType type;

    @Schema(description = "생성일", example = "2025-01-01 12:00:00")
    private LocalDateTime createdAt;

    @Schema(description = "수정일", example = "2025-01-01 12:00:00")
    private LocalDateTime updatedAt;

    public static ExerciseCreateResponse of(ExerciseEntity saveExercise) {
        return ExerciseCreateResponse.builder()
            .exerciseId(saveExercise.getId())
            .name(saveExercise.getName())
            .description(saveExercise.getDescription())
            .sort(saveExercise.getSort())
            .status(saveExercise.getStatus())
            .type(saveExercise.getType())
            .createdAt(saveExercise.getCreatedAt())
            .updatedAt(saveExercise.getUpdatedAt())
            .build();
    }
}
