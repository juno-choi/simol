package com.simol.ouncommon.health.service;

import com.simol.ouncommon.health.dto.HealthSetCreateRequest;
import com.simol.ouncommon.health.vo.HealthSetCreateResponse;
import com.simol.ouncommon.health.vo.HealthSetResponse;

public interface HealthSetService {
    HealthSetCreateResponse createHealthSet(HealthSetCreateRequest healthSetCreateRequest);

    HealthSetResponse getHealthSet(Long healthSetId);
}
