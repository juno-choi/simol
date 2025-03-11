package com.simol.ouncommon.health.vo;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.simol.ouncommon.health.entity.HealthEntity;
import com.simol.ouncommon.health.enums.HealthStatus;
import com.simol.ouncommon.health.enums.HealthType;

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
public class HealthCreateResponse {
    @Schema(description = "health id", example = "1")
    private Long healthId;

    @Schema(description = "건강 이름", example = "스쿼트")
    private String name;

    @Schema(description = "설명", example = "하체 부시기")
    private String description;

    @Schema(description = "순서", example = "1")
    private int sort;

    @Schema(description = "상태", example = "활성")
    private HealthStatus status;

    @Schema(description = "운동 타입", example = "WEIGHT")
    private HealthType healthType;

    @Schema(description = "생성일", example = "2025-01-01 12:00:00")
    private LocalDateTime createdAt;

    @Schema(description = "수정일", example = "2025-01-01 12:00:00")
    private LocalDateTime updatedAt;

    public static HealthCreateResponse of(HealthEntity saveHealth) {
        return HealthCreateResponse.builder()
            .healthId(saveHealth.getId())
            .name(saveHealth.getName())
            .description(saveHealth.getDescription())
            .sort(saveHealth.getSort())
            .status(saveHealth.getStatus())
            .healthType(saveHealth.getHealthType())
            .createdAt(saveHealth.getCreatedAt())
            .updatedAt(saveHealth.getUpdatedAt())
            .build();
    }
}
