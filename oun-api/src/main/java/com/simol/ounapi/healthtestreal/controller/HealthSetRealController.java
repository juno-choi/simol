package com.simol.ounapi.healthtestreal.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.simol.ouncommon.api.CommonApi;
import com.simol.ouncommon.healthsetreal.dto.HealthSetRealCreateRequest;
import com.simol.ouncommon.healthsetreal.service.HealthSetRealService;
import com.simol.ouncommon.healthsetreal.vo.HealthSetRealCreateResponse;
import com.simol.ouncommon.healthsetreal.vo.HealthSetRealResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/routine/health/set/real")
@RequiredArgsConstructor
@Tag(name = "05. HealthSetReal", description = "운동 세트 실제 기록 정보 API")
public class HealthSetRealController {
    private final HealthSetRealService healthSetRealService;
    
    @PostMapping
    @Operation(summary = "1. 운동 세트 실제 기록 생성", description = "운동 세트 실제 기록을 생성합니다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "운동 세트 목표 생성 성공", content = @Content(schema = @Schema(implementation = HealthSetRealCreateResponse.class))),
        @ApiResponse(responseCode = "400", description = "운동 세트 목표 생성 실패", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<CommonApi<HealthSetRealCreateResponse>> createHealthSetReal(@RequestBody HealthSetRealCreateRequest healthSetRealCreateRequest) {
        HealthSetRealCreateResponse healthSetRealResponse = healthSetRealService.createHealthSetReal(healthSetRealCreateRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(CommonApi.create(healthSetRealResponse));
    }

    @GetMapping("/{health_set_real_id}")
    @Operation(summary = "2. 운동 세트 실제 기록 조회", description = "운동 세트 실제 기록을 조회합니다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "운동 세트 실제 기록 조회 성공", content = @Content(schema = @Schema(implementation = HealthSetRealResponse.class))),
        @ApiResponse(responseCode = "400", description = "운동 세트 실제 기록 조회 실패", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<CommonApi<HealthSetRealResponse>> getHealthSetReal(@PathVariable(name = "health_set_real_id") Long healthSetRealId) {
        HealthSetRealResponse healthSetRealResponse = healthSetRealService.getHealthSetReal(healthSetRealId);
        return ResponseEntity.ok(CommonApi.success(healthSetRealResponse));
    }
}
