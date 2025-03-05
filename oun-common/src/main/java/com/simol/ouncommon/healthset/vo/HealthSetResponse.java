package com.simol.ouncommon.healthset.vo;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.simol.ouncommon.healthset.entity.HealthSetEntity;
import com.simol.ouncommon.healthset.enums.HealthSetStatus;

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
public class HealthSetResponse {
    private Long healthSetId;
    private String description;
    private HealthSetStatus status;
    private int setNumber;
    private int setCount;
    private int setWeight;
    private int setDistance;
    private int setTime;
    private int setSpeed;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static HealthSetResponse of(HealthSetEntity healthSet) {
        return HealthSetResponse.builder()
            .healthSetId(healthSet.getId())
            .description(healthSet.getDescription())
            .status(healthSet.getStatus())
            .setNumber(healthSet.getSetNumber())
            .setCount(healthSet.getSetCount())
            .setWeight(healthSet.getSetWeight())
            .setDistance(healthSet.getSetDistance())
            .setTime(healthSet.getSetTime())
            .setSpeed(healthSet.getSetSpeed())
            .createdAt(healthSet.getCreatedAt())
            .updatedAt(healthSet.getUpdatedAt())
            .build();
    }
}
