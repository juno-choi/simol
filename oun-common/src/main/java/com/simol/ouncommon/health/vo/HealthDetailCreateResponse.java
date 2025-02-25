package com.simol.ouncommon.health.vo;

import com.simol.ouncommon.health.entity.HealthDetailEntity;
import com.simol.ouncommon.health.enums.HealthDetailStatus;

import lombok.Builder;

@Builder
public class HealthDetailCreateResponse {
    private Long id;
    private String name;
    private String description;
    private int set;
    private int count;
    private int weight;
    private int distance;
    private int speed;
    private HealthDetailStatus status;

    public static HealthDetailCreateResponse of(HealthDetailEntity healthDetailEntity) {
        return HealthDetailCreateResponse.builder()
            .id(healthDetailEntity.getId())
            .name(healthDetailEntity.getName())
            .description(healthDetailEntity.getDescription())
            .set(healthDetailEntity.getSet())
            .count(healthDetailEntity.getCount())
            .weight(healthDetailEntity.getWeight())
            .distance(healthDetailEntity.getDistance())
            .speed(healthDetailEntity.getSpeed())
            .status(healthDetailEntity.getStatus())
            .build();
    }   
}
