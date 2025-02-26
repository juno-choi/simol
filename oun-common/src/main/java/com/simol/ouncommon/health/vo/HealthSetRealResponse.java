package com.simol.ouncommon.health.vo;

import java.time.LocalDateTime;

import com.simol.ouncommon.health.entity.HealthSetRealEntity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HealthSetRealResponse {
    private Long healthSetRealId;
    private int number;
    private double weight;
    private double distance;
    private int time;
    private double speed;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static HealthSetRealResponse of(HealthSetRealEntity healthSetReal) {
        return HealthSetRealResponse.builder()
            .healthSetRealId(healthSetReal.getId())
            .number(healthSetReal.getNumber())
            .weight(healthSetReal.getWeight())
            .distance(healthSetReal.getDistance())
            .time(healthSetReal.getTime())
            .speed(healthSetReal.getSpeed())
            .createdAt(healthSetReal.getCreatedAt())
            .updatedAt(healthSetReal.getUpdatedAt())
            .build();
    }
}
