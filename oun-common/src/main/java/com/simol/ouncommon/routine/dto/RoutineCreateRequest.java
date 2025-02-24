package com.simol.ouncommon.routine.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Schema(description = "루틴 생성 요청")
public class RoutineCreateRequest {
    @Schema(description = "루틴 이름")
    @NotNull(message = "루틴 이름은 필수 입력 값입니다.")
    private String name;

    @Schema(description = "루틴 설명")
    private String description;
}
