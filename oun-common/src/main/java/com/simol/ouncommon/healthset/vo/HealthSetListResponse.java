package com.simol.ouncommon.healthset.vo;

import java.util.List;

import org.springframework.data.domain.Page;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.simol.ouncommon.healthset.entity.HealthSetEntity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HealthSetListResponse {
    @Schema(description = "마지막 페이지 여부", example = "false")
    private boolean isLast;
    @Schema(description = "비어있는 페이지 여부", example = "false")
    private boolean isEmpty;
    @Schema(description = "총 페이지 수", example = "10")
    private int totalPages;
    @Schema(description = "총 요소 수", example = "100")
    private long totalElements;
    @Schema(description = "현재 페이지의 요소 수", example = "10")
    private int numberOfElements;
    @Schema(description = "운동 세트 목록")
    private List<HealthSetResponse> healthSetList;

    public static HealthSetListResponse of(Page<HealthSetEntity> healthSetEntityPage) {
        List<HealthSetResponse> healthSetList = healthSetEntityPage.stream().map(HealthSetResponse::of).toList();

        return HealthSetListResponse.builder()
            .isLast(healthSetEntityPage.isLast())
            .isEmpty(healthSetEntityPage.isEmpty())
            .totalPages(healthSetEntityPage.getTotalPages())
            .totalElements(healthSetEntityPage.getTotalElements())
            .numberOfElements(healthSetEntityPage.getNumberOfElements())
            .healthSetList(healthSetList)
            .build();
    }
}
