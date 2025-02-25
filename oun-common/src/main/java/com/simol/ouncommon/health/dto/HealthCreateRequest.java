package com.simol.ouncommon.health.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

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
public class HealthCreateRequest {
    @Schema(description = "루틴 ID", example = "1")
    @JsonProperty("routine_id")
    private Long routineId;

    @Schema(description = "건강 이름", example = "스쿼트")
    private String name;

    @Schema(description = "설명", example = "허리 꽂꽂히 아래에 내려가서는 천천히 올라오기!")
    private String description;
}
