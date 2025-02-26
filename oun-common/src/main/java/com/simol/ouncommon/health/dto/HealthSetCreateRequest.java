package com.simol.ouncommon.health.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class HealthSetCreateRequest {
    @NotNull(message = "운동 아이디는 필수 입력 값입니다.")
    @Schema(description = "운동 아이디", example = "1")
    @JsonProperty("health_id")
    private Long healthId;

    @Schema(description = "설명", example = "스쿼트 설명")
    private String description;

    @Schema(description = "정렬", example = "1")
    private int sort;
}
