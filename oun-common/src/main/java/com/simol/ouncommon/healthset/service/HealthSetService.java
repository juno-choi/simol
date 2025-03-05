package com.simol.ouncommon.healthset.service;

import org.springframework.data.domain.Pageable;

import com.simol.ouncommon.healthset.dto.HealthSetCreateRequest;
import com.simol.ouncommon.healthset.vo.HealthSetCreateResponse;
import com.simol.ouncommon.healthset.vo.HealthSetResponse;

public interface HealthSetService {
    HealthSetCreateResponse createHealthSet(HealthSetCreateRequest healthSetCreateRequest);

    HealthSetResponse getHealthSet(Long healthSetId);

    HealthSetListResponse getHealthSetList(Pageable pageable, Long healthId);
}
