package com.simol.ouncommon.health.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.simol.ouncommon.health.entity.HealthDetailEntity;
import com.simol.ouncommon.health.enums.HealthDetailStatus;

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
public class HealthDetailCreateResponse {
    private Long id;
    private String description;
    private int sets;
    private int count;
    private int weight;
    private int distance;
    private int speed;
    private int time;
    private HealthDetailStatus status;

    public static HealthDetailCreateResponse of(HealthDetailEntity healthDetailEntity) {
        return HealthDetailCreateResponse.builder()
            .id(healthDetailEntity.getId())
            .description(healthDetailEntity.getDescription())
            .sets(healthDetailEntity.getSets())
            .count(healthDetailEntity.getCount())
            .weight(healthDetailEntity.getWeight())
            .distance(healthDetailEntity.getDistance())
            .speed(healthDetailEntity.getSpeed())
            .time(healthDetailEntity.getTime())
            .status(healthDetailEntity.getStatus())
            .build();
    }   
}
