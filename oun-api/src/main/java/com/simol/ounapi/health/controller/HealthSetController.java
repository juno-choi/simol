package com.simol.ounapi.health.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.simol.ouncommon.api.CommonApi;
import com.simol.ouncommon.health.dto.HealthSetCreateRequest;
import com.simol.ouncommon.health.service.HealthSetService;
import com.simol.ouncommon.health.vo.HealthSetCreateResponse;
import com.simol.ouncommon.health.vo.HealthSetResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;    
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/routine/health/set")
@Tag(name = "03. HealthSet", description = "운동 세트 정보 API")
@RequiredArgsConstructor
public class HealthSetController {
    private final HealthSetService healthDetailService;

    @PostMapping
    @Operation(summary = "1. 운동 세트 생성", description = "운동 세트를 생성합니다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "운동 세트 생성 성공", content = @Content(schema = @Schema(implementation = HealthSetCreateResponse.class))),
        @ApiResponse(responseCode = "400", description = "운동 세트 생성 실패", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<CommonApi<HealthSetCreateResponse>> createHealthSet(@RequestBody @Validated HealthSetCreateRequest healthDetailCreateRequest, HttpServletRequest request) {
        HealthSetCreateResponse healthSetCreateResponse = healthDetailService.createHealthSet(healthDetailCreateRequest, request);
        return ResponseEntity.ok(CommonApi.create(healthSetCreateResponse));
    }

    @GetMapping("/{health_set_id}")
    @Operation(summary = "2. 운동 세트 조회", description = "운동 세트를 조회합니다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "운동 세트 조회 성공", content = @Content(schema = @Schema(implementation = HealthSetResponse.class))),
        @ApiResponse(responseCode = "400", description = "운동 세트 조회 실패", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<CommonApi<HealthSetResponse>> getHealthSet(@PathVariable(name = "health_set_id") Long healthSetId) {
        HealthSetResponse healthSetResponse = healthDetailService.getHealthSet(healthSetId);
        return ResponseEntity.ok(CommonApi.create(healthSetResponse));
    }
    
}
