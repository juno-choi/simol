package com.simol.ouncommon.exercise.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.simol.ouncommon.exercise.enums.ExerciseStatus;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ExerciseUpdateRequest {
    @Schema(description = "운동 ID", example = "1")
    @NotNull(message = "운동 ID는 필수 입력 값입니다.")
    @JsonProperty("exercise_id")
    private Long exerciseId;

    @Schema(description = "운동 이름", example = "스쿼트")
    @NotNull(message = "운동 이름은 필수 입력 값입니다.")
    private String name;

    @Schema(description = "운동 설명", example = "허리 꽂꽂히 아래에 내려가서는 빨리 올라오기!")
    private String description;

    @Schema(description = "운동 순서", example = "1")
    @NotNull(message = "운동 순서는 필수 입력 값입니다.")
    private int sort;

    @Schema(description = "운동 상태", example = "INACTIVE")
    @NotNull(message = "운동 상태는 필수 입력 값입니다.")
    private ExerciseStatus status;

    @ArraySchema(
        arraySchema = @Schema(
            description = "루틴 운동 세트 목록", 
            example = "[{\"exercise_set_id\": 1, \"type\": \"WEIGHT\", \"number\": 1, \"count\": 10, \"weight\": 10, \"distance\": 0, \"time\": 0, \"speed\": 0, \"description\": \"스쿼트 설명\"}]"
        )
    )
    @JsonProperty("exercise_set_list")
    private List<ExerciseSetListUpdateRequest> exerciseSetList;

    @Builder
    public ExerciseUpdateRequest(Long exerciseId, String name, String description, int sort, ExerciseStatus status, List<ExerciseSetListUpdateRequest> exerciseSetList) {
        this.exerciseId = exerciseId;
        this.name = name;
        this.description = description;
        this.sort = sort;
        this.status = status;
        this.exerciseSetList = exerciseSetList;
    }
}
