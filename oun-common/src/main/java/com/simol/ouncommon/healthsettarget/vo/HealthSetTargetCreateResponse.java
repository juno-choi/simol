package com.simol.ouncommon.healthsettarget.vo;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.simol.ouncommon.healthsettarget.entity.HealthSetTargetEntity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class HealthSetTargetCreateResponse {
    private Long healthSetTargetId;

    private Long healthSetId;

    private int number;

    private double weight;

    private double distance;

    private int time;

    private double speed;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public static HealthSetTargetCreateResponse of(HealthSetTargetEntity healthSetTarget) {
        return HealthSetTargetCreateResponse.builder()
            .healthSetTargetId(healthSetTarget.getId())
            .healthSetId(healthSetTarget.getHealthSet().getId())
            .number(healthSetTarget.getNumber())
            .weight(healthSetTarget.getWeight())
            .distance(healthSetTarget.getDistance())
            .time(healthSetTarget.getTime())
            .speed(healthSetTarget.getSpeed())
            .build();
    }
}
