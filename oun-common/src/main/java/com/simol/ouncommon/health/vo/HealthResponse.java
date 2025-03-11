package com.simol.ouncommon.health.vo;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.simol.ouncommon.health.entity.HealthEntity;
import com.simol.ouncommon.health.enums.HealthStatus;
import com.simol.ouncommon.health.enums.HealthType;
import com.simol.ouncommon.healthset.vo.HealthSetResponse;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Getter
@Builder
public class HealthResponse {
    @Schema(description = "운동 아이디", example = "1")
    private Long healthId;
    @Schema(description = "운동 이름", example = "푸시업")
    private String name;
    @Schema(description = "운동 설명", example = "푸시업 설명")
    private String description;
    @Schema(description = "운동 순서", example = "1")
    private int sort;
    @Schema(description = "운동 상태", example = "ACTIVE")
    private HealthStatus status;
    @Schema(description = "운동 타입", example = "WEIGHT")
    private HealthType healthType;

    @Schema(description = "운동 생성일", example = "2025-01-01 00:00:00")
    private LocalDateTime createdAt;
    @Schema(description = "운동 수정일", example = "2025-01-01 00:00:00")
    private LocalDateTime updatedAt;

    @Schema(description = "운동 세트 목록", example = "[]")
    private List<HealthSetResponse> healthSetList;

    public static HealthResponse of(HealthEntity health) {
        return HealthResponse.builder()
            .healthId(health.getId())
            .name(health.getName())
            .description(health.getDescription())
            .sort(health.getSort())
            .status(health.getStatus())
            .healthType(health.getHealthType())
            .healthSetList(health.getHealthSetList().stream().map(HealthSetResponse::of).toList())
            .createdAt(health.getCreatedAt())
            .updatedAt(health.getUpdatedAt())
            .build();
    }
}
