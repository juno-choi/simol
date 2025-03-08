package com.simol.ouncommon.routine.vo;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.simol.ouncommon.routine.entity.RoutineEntity;
import com.simol.ouncommon.routine.enums.RoutineStatus;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@Schema(description = "루틴 생성 응답")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class RoutineCreateResponse {
    @Schema(description = "루틴 ID", example = "1")
    private Long routineId;
    @Schema(description = "루틴 이름", example = "월요일 루틴")
    private String name;
    @Schema(description = "루틴 설명", example = "하체 부시기")
    private String description;
    @Schema(description = "루틴 상태", example = "ACTIVE")
    private RoutineStatus status;
    @Schema(description = "루틴 생성일", example = "2025-01-01T00:00:00")
    private LocalDateTime createdAt;
    @Schema(description = "루틴 수정일", example = "2025-01-01T00:00:00")
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