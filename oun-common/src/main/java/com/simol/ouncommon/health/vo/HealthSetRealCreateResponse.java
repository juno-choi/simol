package com.simol.ouncommon.health.vo;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class HealthSetRealCreateResponse {
    private Long healthSetRealId;

    private Long healthSetId;

    private int number;

    private double weight;

    private double distance;

    private int time;

    private double speed;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public static HealthSetRealCreateResponse of(HealthSetRealEntity healthSetReal) {
        return HealthSetRealCreateResponse.builder()
            .healthSetRealId(healthSetReal.getId())
            .healthSetId(healthSetReal.getHealthSet().getId())
            .number(healthSetReal.getNumber())
            .weight(healthSetReal.getWeight())
            .distance(healthSetReal.getDistance())
            .time(healthSetReal.getTime())
            .speed(healthSetReal.getSpeed())
            .build();
    }
}
