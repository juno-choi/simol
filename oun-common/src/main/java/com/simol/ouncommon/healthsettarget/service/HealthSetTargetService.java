package com.simol.ouncommon.healthsettarget.service;

import com.simol.ouncommon.healthsettarget.dto.HealthSetTargetCreateRequest;
import com.simol.ouncommon.healthsettarget.vo.HealthSetTargetCreateResponse;
import com.simol.ouncommon.healthsettarget.vo.HealthSetTargetResponse;

public interface HealthSetTargetService {
    HealthSetTargetCreateResponse createHealthSetTarget(HealthSetTargetCreateRequest healthSetTargetCreateRequest);
    HealthSetTargetResponse getHealthSetTarget(Long healthSetTargetId);
}
