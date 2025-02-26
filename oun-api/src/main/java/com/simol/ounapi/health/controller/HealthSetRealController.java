package com.simol.ounapi.health.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.simol.ouncommon.api.CommonApi;
import com.simol.ouncommon.health.dto.HealthSetTargetCreateRequest;
import com.simol.ouncommon.health.service.HealthSetRealService;
import com.simol.ouncommon.health.service.HealthSetTargetService;
import com.simol.ouncommon.health.vo.HealthSetTargetCreateResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/routine/health/set/target")
@RequiredArgsConstructor
@Tag(name = "04. HealthSetTarget", description = "운동 세트 목표 정보 API")
public class HealthSetRealController {
    private final HealthSetRealService healthSetRealService;
    
    @PostMapping
    @Operation(summary = "1. 운동 세트 목표 생성", description = "운동 세트 목표를 생성합니다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "운동 세트 목표 생성 성공", content = @Content(schema = @Schema(implementation = HealthSetTargetCreateResponse.class))),
        @ApiResponse(responseCode = "400", description = "운동 세트 목표 생성 실패", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<CommonApi<HealthSetTargetCreateResponse>> create(@RequestBody HealthSetTargetCreateRequest healthSetTargetCreateRequest) {
        HealthSetTargetCreateResponse healthSetTargetCreateResponse = healthSetRealService.createHealthSetTarget(healthSetTargetCreateRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(CommonApi.create(healthSetTargetCreateResponse));
    }
}
