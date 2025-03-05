package com.simol.ouncommon.healthset.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.simol.ouncommon.healthset.entity.HealthSetEntity;
import com.simol.ouncommon.healthset.enums.HealthSetStatus;

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
    private HealthSetStatus status;

    public static HealthSetCreateResponse of(HealthSetEntity healthSetEntity) {
        return HealthSetCreateResponse.builder()
            .id(healthSetEntity.getId())
            .description(healthSetEntity.getDescription())
            .sort(healthSetEntity.getSort())
            .status(healthSetEntity.getStatus())
            .build();
    }   
}
