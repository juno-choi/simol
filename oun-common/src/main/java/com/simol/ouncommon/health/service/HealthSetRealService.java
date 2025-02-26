package com.simol.ouncommon.health.service;

import com.simol.ouncommon.health.dto.HealthSetRealCreateRequest;
import com.simol.ouncommon.health.vo.HealthSetRealCreateResponse;

public interface HealthSetRealService {
    HealthSetRealCreateResponse create(HealthSetRealCreateRequest healthSetRealCreateRequest);
}
