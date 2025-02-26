package com.simol.ouncommon.health.dto;

import java.util.Optional;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HealthDetailCreateRequest {
    @NotNull(message = "운동 아이디는 필수 입력 값입니다.")
    @Schema(description = "운동 아이디", example = "1")
    private Long healthId;

    @Schema(description = "설명", example = "팔굽혀펴기 설명")
    private String description;

    @NotNull(message = "세트는 필수 입력 값입니다.")
    @Schema(description = "세트(정렬 기준)", example = "10")
    private int set;

    @Schema(description = "횟수", example = "10")
    private int count;

    @Schema(description = "무게(kg)", example = "10")
    private int weight;

    @Schema(description = "거리(km)", example = "10")
    private int distance;

    @Schema(description = "속도(km/h)", example = "10")
    private int speed;

    @Schema(description = "시간(초)", example = "300")
    private int time;

    public Long getHealthId() {
        return healthId;
    }

    public String getDescription() {
        return description;
    }

    public int getSet() {
        return set;
    }

    public int getCount() {
        return Optional.ofNullable(count).orElse(0);
    }

    public int getWeight() {
        return Optional.ofNullable(weight).orElse(0);
    }

    public int getDistance() {
        return Optional.ofNullable(distance).orElse(0);
    }

    public int getSpeed() {
        return Optional.ofNullable(speed).orElse(0);
    }

    public int getTime() {
        return Optional.ofNullable(time).orElse(0);
    }
    
}
