package com.simol.ounapi.health.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.simol.ouncommon.api.CommonApi;
import com.simol.ouncommon.health.dto.HealthSetCreateRequest;
import com.simol.ouncommon.health.service.HealthDetailService;
import com.simol.ouncommon.health.vo.HealthSetCreateResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
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
    private final HealthDetailService healthDetailService;

    @PostMapping
    @Operation(summary = "1. 운동 세트 생성", description = "운동 세트를 생성합니다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "운동 세트 생성 성공", content = @Content(schema = @Schema(implementation = HealthSetCreateResponse.class))),
        @ApiResponse(responseCode = "400", description = "운동 세트 생성 실패", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<CommonApi<HealthSetCreateResponse>> createHealthDetail(@RequestBody @Validated HealthSetCreateRequest healthDetailCreateRequest, HttpServletRequest request) {
        HealthSetCreateResponse healthDetailCreateResponse = healthDetailService.createHealthDetail(healthDetailCreateRequest, request);
        return ResponseEntity.ok(CommonApi.create(healthDetailCreateResponse));
    }
}
