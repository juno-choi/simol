package com.simol.ouncommon.routine.vo;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.simol.ouncommon.routine.entity.RoutineEntity;
import com.simol.ouncommon.routine.enums.RoutineStatus;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class RoutineResponse {
    @JsonProperty("routine_id")
    private Long routineId;
    private String name;
    private String description;
    private RoutineStatus status;
    @JsonProperty("created_at")
    private LocalDateTime createdAt;
    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;

    public static RoutineResponse of(RoutineEntity routine) {
        return RoutineResponse.builder()
            .routineId(routine.getId())
            .name(routine.getName())
            .description(routine.getDescription())
            .status(routine.getStatus())
            .createdAt(routine.getCreatedAt())
            .updatedAt(routine.getUpdatedAt())
            .build();
    }
}
