package com.simol.ouncommon.healthset.vo;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.simol.ouncommon.healthset.entity.HealthSetEntity;
import com.simol.ouncommon.healthset.enums.HealthSetStatus;

import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(description = "healthSetId", example = "1")
    private Long id;

    @Schema(description = "description", example = "description")
    private String description;

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
    private int setSpeed;

    @Schema(description = "상태", example = "ACTIVE")
    private HealthSetStatus status;

    @Schema(description = "생성일", example = "2025-01-01T00:00:00")
    private LocalDateTime createdAt;

    @Schema(description = "수정일", example = "2025-01-01T00:00:00")
    private LocalDateTime updatedAt;

    public static HealthSetCreateResponse of(HealthSetEntity healthSetEntity) {
        return HealthSetCreateResponse.builder()
            .id(healthSetEntity.getId())
            .description(healthSetEntity.getDescription())
            .setNumber(healthSetEntity.getSetNumber())
            .setCount(healthSetEntity.getSetCount())
            .setWeight(healthSetEntity.getSetWeight())
            .setDistance(healthSetEntity.getSetDistance())
            .setTime(healthSetEntity.getSetTime())
            .setSpeed(healthSetEntity.getSetSpeed())
            .status(healthSetEntity.getStatus())
            .build();
    }   
}
