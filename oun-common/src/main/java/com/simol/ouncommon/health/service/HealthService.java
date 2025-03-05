package com.simol.ouncommon.health.service;

import com.simol.ouncommon.health.dto.HealthCreateRequest;
import com.simol.ouncommon.health.dto.HealthUpdateRequest;
import com.simol.ouncommon.health.vo.HealthCreateResponse;
import com.simol.ouncommon.health.vo.HealthListResponse;
import com.simol.ouncommon.health.vo.HealthResponse;

import jakarta.servlet.http.HttpServletRequest;

public interface HealthService {
    HealthCreateResponse createHealth(HealthCreateRequest healthCreateRequest, HttpServletRequest request);
    HealthResponse getHealth(Long healthId);
    HealthListResponse getHealthList(int page, int size, HttpServletRequest request);
    HealthResponse updateHealth(HealthUpdateRequest healthUpdateRequest, HttpServletRequest request);
}
