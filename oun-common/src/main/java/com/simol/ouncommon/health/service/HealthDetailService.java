package com.simol.ouncommon.health.service;

import com.simol.ouncommon.health.dto.HealthDetailCreateRequest;
import com.simol.ouncommon.health.vo.HealthDetailCreateResponse;

public interface HealthDetailService {
    HealthDetailCreateResponse createHealthDetail(HealthDetailCreateRequest healthDetailCreateRequest);
}
