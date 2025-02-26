package com.simol.ouncommon.health.service;

import com.simol.ouncommon.health.dto.HealthSetCreateRequest;
import com.simol.ouncommon.health.vo.HealthSetCreateResponse;
import com.simol.ouncommon.health.vo.HealthSetResponse;

import jakarta.servlet.http.HttpServletRequest;

public interface HealthSetService {
    HealthSetCreateResponse createHealthSet(HealthSetCreateRequest healthSetCreateRequest, HttpServletRequest request);

    HealthSetResponse getHealthSet(Long healthSetId);
}
