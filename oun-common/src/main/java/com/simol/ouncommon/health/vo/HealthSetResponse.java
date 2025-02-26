package com.simol.ouncommon.health.vo;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.simol.ouncommon.health.entity.HealthSetEntity;
import com.simol.ouncommon.health.enums.HealthSetStatus;

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
    private int sort;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static HealthSetResponse of(HealthSetEntity healthSet) {
        return HealthSetResponse.builder()
            .healthSetId(healthSet.getId())
            .description(healthSet.getDescription())
            .status(healthSet.getStatus())
            .sort(healthSet.getSort())
            .createdAt(healthSet.getCreatedAt())
            .updatedAt(healthSet.getUpdatedAt())
            .build();
    }
}
