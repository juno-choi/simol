package com.simol.ouncommon.health.service;

import com.simol.ouncommon.health.dto.HealthSetTargetCreateRequest;
import com.simol.ouncommon.health.vo.HealthSetTargetCreateResponse;
import com.simol.ouncommon.health.vo.HealthSetTargetResponse;

public interface HealthSetTargetService {
    HealthSetTargetCreateResponse createHealthSetTarget(HealthSetTargetCreateRequest healthSetTargetCreateRequest);
    HealthSetTargetResponse getHealthSetTarget(Long healthSetTargetId);
}
