package com.simol.ounapi.health.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.simol.ouncommon.api.CommonApi;
import com.simol.ouncommon.health.dto.HealthSetCreateRequest;
import com.simol.ouncommon.health.service.HealthDetailService;
import com.simol.ouncommon.health.vo.HealthSetCreateResponse;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/health/detail")
@Tag(name = "HealthDetail", description = "운동 상세 정보 API")
@RequiredArgsConstructor
public class HealthDetailController {
    private final HealthDetailService healthDetailService;

    @PostMapping
    public ResponseEntity<CommonApi<HealthSetCreateResponse>> createHealthDetail(@RequestBody @Validated HealthSetCreateRequest healthDetailCreateRequest, HttpServletRequest request) {
        HealthSetCreateResponse healthDetailCreateResponse = healthDetailService.createHealthDetail(healthDetailCreateRequest, request);
        return ResponseEntity.ok(CommonApi.create(healthDetailCreateResponse));
    }
}
