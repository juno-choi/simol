package com.simol.ouncommon.routine.vo;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.simol.ouncommon.routine.entity.RoutineEntity;
import com.simol.ouncommon.routine.enums.RoutineStatus;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@Schema(description = "루틴 생성 응답")
public class RoutineCreateResponse {
    @Schema(description = "루틴 ID", example = "1")
    @JsonProperty("routine_id")
    private Long routineId;
    @Schema(description = "루틴 이름", example = "월요일 루틴")
    @JsonProperty("name")
    private String name;
    @Schema(description = "루틴 설명", example = "하체 부시기")
    @JsonProperty("description")
    private String description;
    @Schema(description = "루틴 상태", example = "ACTIVE")
    @JsonProperty("status")
    private RoutineStatus status;
    @Schema(description = "루틴 생성일", example = "2025-01-01T00:00:00")
    @JsonProperty("created_at")
    private LocalDateTime createdAt;
    @Schema(description = "루틴 수정일", example = "2025-01-01T00:00:00")
    @JsonProperty("updated_at") 
    private LocalDateTime updatedAt;
    
    public static RoutineCreateResponse of(RoutineEntity routineEntity) {
        return RoutineCreateResponse.builder()
            .routineId(routineEntity.getId())
            .name(routineEntity.getName())
            .description(routineEntity.getDescription())
            .status(routineEntity.getStatus())
            .createdAt(routineEntity.getCreatedAt())
            .updatedAt(routineEntity.getUpdatedAt())
            .build();
    }
}