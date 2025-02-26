package com.simol.ouncommon.health.service;

import com.simol.ouncommon.health.dto.HealthSetRealCreateRequest;
import com.simol.ouncommon.health.vo.HealthSetRealCreateResponse;
import com.simol.ouncommon.health.vo.HealthSetRealResponse;

public interface HealthSetRealService {
    HealthSetRealCreateResponse createHealthSetReal(HealthSetRealCreateRequest healthSetRealCreateRequest);
    HealthSetRealResponse getHealthSetReal(Long healthSetRealId);
}
