package com.simol.ouncommon.routine.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.simol.ouncommon.exercise.enums.ExerciseStatus;
import com.simol.ouncommon.exercise.enums.ExerciseType;

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
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoutineExerciseUpdateRequest {
    @Schema(description = "운동 id", example = "1")
    private Long exerciseId;
    
    @Schema(description = "운동 이름", example = "스쿼트")
    @NotNull(message = "운동 이름은 필수 입력 값입니다.")
    private String name;

    @Schema(description = "운동 설명", example = "허리 꽂꽂히 아래에 내려가서는 빨리 올라오기!222")
    private String description;

    @Schema(description = "운동 순서", example = "1")
    @NotNull(message = "운동 순서는 필수 입력 값입니다.")
    private int sort;

    @Schema(description = "운동 상태", example = "ACTIVE")
    @NotNull(message = "운동 상태는 필수 입력 값입니다.")
    private ExerciseStatus status;

    @Schema(description = "운동 타입", example = "WEIGHT")
    @NotNull(message = "운동 타입은 필수 입력 값입니다.")
    private ExerciseType type;
}
