package com.simol.ouncommon.healthsetreal.service;

import com.simol.ouncommon.healthsetreal.dto.HealthSetRealCreateRequest;
import com.simol.ouncommon.healthsetreal.vo.HealthSetRealCreateResponse;
import com.simol.ouncommon.healthsetreal.vo.HealthSetRealResponse;

public interface HealthSetRealService {
    HealthSetRealCreateResponse createHealthSetReal(HealthSetRealCreateRequest healthSetRealCreateRequest);
    HealthSetRealResponse getHealthSetReal(Long healthSetRealId);
}
