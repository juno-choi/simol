package com.simol.simolcommon.oun.routine.dto;

import java.util.List;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.simol.simolcommon.oun.routine.enums.RoutineStatus;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Schema(description = "루틴 생성 요청")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RoutineUpdateRequest {
    @Schema(description = "루틴 아이디", example = "1")
    @NotNull(message = "루틴 아이디는 필수 입력 값입니다.")
    @JsonProperty("routine_id")
    private long routineId;

    @Schema(description = "루틴 이름", example = "월요일 루틴")
    @NotNull(message = "루틴 이름은 필수 입력 값입니다.")
    private String name;

    @Schema(description = "루틴 설명", example = "하체 부시는 날222")
    private String description;

    @Schema(description = "루틴 상태(활성화 : ACTIVE, 비활성화 : INACTIVE)", example = "ACTIVE")
    @NotNull(message = "루틴 상태는 필수 입력 값입니다.")
    private RoutineStatus status;

    @ArraySchema(
        arraySchema = @Schema(
            description = "루틴 exercise 목록", 
            example = "[{\"exercise_id\": 1, \"name\": \"스쿼트\", \"description\": \"허리 꽂꽂히 아래에 내려가서는 빨리 올라오기!\", \"sort\": 1, \"status\": \"ACTIVE\", \"type\": \"WEIGHT\" }]"
        )
    )
    @JsonProperty("routine_exercise_list")
    private List<RoutineExerciseUpdateRequest> exerciseList = new ArrayList<>();

    @Builder
    public RoutineUpdateRequest(long routineId, String name, String description, RoutineStatus status, List<RoutineExerciseUpdateRequest> exerciseList) {
        this.routineId = routineId;
        this.name = name;
        this.description = description;
        this.status = status;
        this.exerciseList = exerciseList;
    }
}
