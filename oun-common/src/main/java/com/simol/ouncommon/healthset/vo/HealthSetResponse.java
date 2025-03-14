package com.simol.ouncommon.healthset.vo;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.simol.ouncommon.healthset.entity.HealthSetEntity;
import com.simol.ouncommon.healthset.enums.HealthSetStatus;

import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(description = "healthSetId", example = "1")
    private Long healthSetId;

    @Schema(description = "description", example = "description")
    private String description;

    @Schema(description = "status", example = "ACTIVE")
    private HealthSetStatus status;

    @Schema(description = "세트 번호 (정렬)", example = "1")
    private int setNumber;

    @Schema(description = "세트 횟수", example = "5")
    private int setCount;

    @Schema(description = "세트 무게", example = "100")
    private int setWeight;

    @Schema(description = "세트 거리", example = "100")
    private int setDistance;

    @Schema(description = "세트 시간", example = "100")
    private int setTime;

    @Schema(description = "세트 속도", example = "100")
    private double setSpeed;

    @Schema(description = "생성일", example = "2025-01-01T00:00:00")
    private LocalDateTime createdAt;

    @Schema(description = "수정일", example = "2025-01-01T00:00:00")
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
