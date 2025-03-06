package com.simol.ouncommon.health.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.simol.ouncommon.health.enums.HealthStatus;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HealthUpdateRequest {
    @Schema(description = "운동 ID", example = "1")
    @NotNull(message = "운동 ID는 필수 입력 값입니다.")
    @JsonProperty("health_id")
    private Long healthId;

    @Schema(description = "운동 이름", example = "스쿼트")
    @NotNull(message = "운동 이름은 필수 입력 값입니다.")
    private String name;

    @Schema(description = "운동 설명", example = "허리 꽂꽂히 아래에 내려가서는 빨리 올라오기!")
    private String description;

    @Schema(description = "운동 순서", example = "1")
    @NotNull(message = "운동 순서는 필수 입력 값입니다.")
    private int sort;

    @Schema(description = "운동 상태", example = "INACTIVE")
    @NotNull(message = "운동 상태는 필수 입력 값입니다.")
    private HealthStatus status;

    @ArraySchema(
        arraySchema = @Schema(
            description = "루틴 health 목록", 
            example = "[{\"health_set_id\": 1, \"health_set_type\": \"WEIGHT\", \"set_number\": 1, \"set_count\": 10, \"set_weight\": 10, \"set_distance\": 0, \"set_time\": 0, \"set_speed\": 0, \"description\": \"스쿼트 설명\"}]"
        )
    )
    @JsonProperty("health_set_list")
    private List<HealthHealthSetUpdateRequest> healthSetList;

    @Builder
    public HealthUpdateRequest(Long healthId, String name, String description, int sort, HealthStatus status, List<HealthHealthSetUpdateRequest> healthSetList) {
        this.healthId = healthId;
        this.name = name;
        this.description = description;
        this.sort = sort;
        this.status = status;
        this.healthSetList = healthSetList;
    }
}
