package com.simol.ouncommon.healthset.service;

import com.simol.ouncommon.healthset.dto.HealthSetCreateRequest;
import com.simol.ouncommon.healthset.vo.HealthSetCreateResponse;
import com.simol.ouncommon.healthset.vo.HealthSetResponse;

public interface HealthSetService {
    HealthSetCreateResponse createHealthSet(HealthSetCreateRequest healthSetCreateRequest);

    HealthSetResponse getHealthSet(Long healthSetId);
}
