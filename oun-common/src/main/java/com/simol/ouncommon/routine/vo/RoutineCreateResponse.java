package com.simol.ouncommon.routine.vo;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.simol.ouncommon.routine.enums.RoutineStatus;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@Schema(description = "루틴 생성 응답")
public class RoutineCreateResponse {
    @Schema(description = "루틴 ID")
    @JsonProperty("routine_id")
    private Long routineId;
    @Schema(description = "루틴 이름")
    @JsonProperty("name")
    private String name;
    @Schema(description = "루틴 설명")
    @JsonProperty("description")
    private String description;
    @Schema(description = "루틴 상태")
    @JsonProperty("status")
    private RoutineStatus status;
    @Schema(description = "루틴 생성일")
    @JsonProperty("created_at")
    private LocalDateTime createdAt;
    @Schema(description = "루틴 수정일")
    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;
}