package com.simol.ouncommon.health.vo;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.simol.ouncommon.health.entity.HealthEntity;
import com.simol.ouncommon.health.enums.HealthStatus;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HealthCreateResponse {
    @Schema(description = "health id", example = "1")
    @JsonProperty("health_id")
    private Long healthId;

    @Schema(description = "건강 이름", example = "스쿼트")
    @JsonProperty("name")
    private String name;

    @Schema(description = "설명", example = "하체 부시기")
    @JsonProperty("description")
    private String description;

    @Schema(description = "순서", example = "1")
    private int sort;

    @Schema(description = "상태", example = "활성")
    @JsonProperty("status")
    private HealthStatus status;

    @Schema(description = "생성일", example = "2025-01-01 12:00:00")
    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @Schema(description = "수정일", example = "2025-01-01 12:00:00")
    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;

    public static HealthCreateResponse create(HealthEntity saveHealth) {
        return HealthCreateResponse.builder()
            .healthId(saveHealth.getId())
            .name(saveHealth.getName())
            .description(saveHealth.getDescription())
            .sort(saveHealth.getSort())
            .status(saveHealth.getStatus())
            .createdAt(saveHealth.getCreatedAt())
            .updatedAt(saveHealth.getUpdatedAt())
            .build();
    }
}
