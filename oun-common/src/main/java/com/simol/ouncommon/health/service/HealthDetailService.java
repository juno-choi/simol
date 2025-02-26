package com.simol.ouncommon.health.service;

import com.simol.ouncommon.health.dto.HealthSetCreateRequest;
import com.simol.ouncommon.health.vo.HealthSetCreateResponse;

import jakarta.servlet.http.HttpServletRequest;

public interface HealthDetailService {
    HealthSetCreateResponse createHealthDetail(HealthSetCreateRequest healthDetailCreateRequest, HttpServletRequest request);
}
