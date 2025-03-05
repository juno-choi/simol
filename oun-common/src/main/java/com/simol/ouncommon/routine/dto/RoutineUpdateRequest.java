package com.simol.ouncommon.routine.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.simol.ouncommon.routine.enums.RoutineStatus;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@Schema(description = "루틴 생성 요청")
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RoutineUpdateRequest {
    @Schema(description = "루틴 아이디", example = "1")
    @NotNull(message = "루틴 아이디는 필수 입력 값입니다.")
    @JsonProperty("routine_id")
    private long routineId;

    @Schema(description = "루틴 이름", example = "월요일 루틴")
    @NotNull(message = "루틴 이름은 필수 입력 값입니다.")
    private String name;

    @Schema(description = "루틴 설명", example = "하체 부시는 날")
    private String description;

    @Schema(description = "루틴 상태(활성화 : ACTIVE, 비활성화 : INACTIVE)", example = "INACTIVE")
    @NotNull(message = "루틴 상태는 필수 입력 값입니다.")
    private RoutineStatus status;
}
