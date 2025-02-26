package com.simol.ouncommon.health.vo;

import java.time.LocalDateTime;

import com.simol.ouncommon.health.entity.HealthSetTargetEntity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HealthSetTargetResponse {
    private Long healthSetTargetId;
    private int number;
    private double weight;
    private double distance;
    private int time;
    private double speed;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static HealthSetTargetResponse of(HealthSetTargetEntity healthSetTarget) {
        return HealthSetTargetResponse.builder()
            .healthSetTargetId(healthSetTarget.getId())
            .number(healthSetTarget.getNumber())
            .weight(healthSetTarget.getWeight())
            .distance(healthSetTarget.getDistance())
            .time(healthSetTarget.getTime())
            .speed(healthSetTarget.getSpeed())
            .createdAt(healthSetTarget.getCreatedAt())
            .updatedAt(healthSetTarget.getUpdatedAt())
            .build();
    }
}
