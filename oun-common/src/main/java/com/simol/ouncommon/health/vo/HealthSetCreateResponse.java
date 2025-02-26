package com.simol.ouncommon.health.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.simol.ouncommon.health.entity.HealthSetEntity;
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
public class HealthSetCreateResponse {
    private Long id;
    private String description;
    private int sort;
    private HealthDetailStatus status;

    public static HealthSetCreateResponse of(HealthSetEntity healthDetailEntity) {
        return HealthSetCreateResponse.builder()
            .id(healthDetailEntity.getId())
            .description(healthDetailEntity.getDescription())
            .sort(healthDetailEntity.getSort())
            .status(healthDetailEntity.getStatus())
            .build();
    }   
}
