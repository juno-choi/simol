package com.simol.simolcommon.oun.routine.vo;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.simol.simolcommon.oun.routine.entity.RoutineEntity;
import com.simol.simolcommon.oun.routine.enums.RoutineStatus;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class RoutineResponse {
    private Long routineId;
    private String name;
    private String description;
    private RoutineStatus status;
    private LocalDateTime createdAt;
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
