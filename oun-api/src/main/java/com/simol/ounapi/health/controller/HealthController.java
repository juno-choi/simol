package com.simol.ounapi.health.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.simol.ouncommon.api.CommonApi;
import com.simol.ouncommon.api.ErrorApi;
import com.simol.ouncommon.health.dto.HealthCreateRequest;
import com.simol.ouncommon.health.service.HealthService;
import com.simol.ouncommon.health.vo.HealthCreateResponse;
import com.simol.ouncommon.health.vo.HealthResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/routine/health")
@RequiredArgsConstructor
@Tag(name = "02. Health", description = "운동 관리 API")
@SecurityRequirement(name = "bearerAuth")
public class HealthController {
    private final HealthService healthService;
    
    @PostMapping
    @Operation(summary = "1. 운동 생성", description = "운동을 생성합니다.", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "success", content = @Content(schema = @Schema(implementation = HealthCreateResponse.class))),
        @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = ErrorApi.class))),
    })
    public ResponseEntity<CommonApi<HealthCreateResponse>> createHealth(@RequestBody HealthCreateRequest healthCreateRequest, HttpServletRequest request) {
        HealthCreateResponse healthCreateResponse = healthService.createHealth(healthCreateRequest, request);
        return ResponseEntity.ok(CommonApi.create(healthCreateResponse));
    }

    @GetMapping("/{healthId}")
    @Operation(summary = "2. 운동 조회", description = "운동을 조회합니다.", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "success", content = @Content(schema = @Schema(implementation = HealthResponse.class))),
        @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = ErrorApi.class))),
    })
    public ResponseEntity<CommonApi<HealthResponse>> getHealth(@PathVariable Long healthId) {
        HealthResponse healthResponse = healthService.getHealth(healthId);
        return ResponseEntity.ok(CommonApi.create(healthResponse));
    }
}
