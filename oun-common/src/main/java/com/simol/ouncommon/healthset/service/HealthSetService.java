package com.simol.ouncommon.healthset.service;

import com.simol.ouncommon.healthset.dto.HealthSetCreateRequest;
import com.simol.ouncommon.healthset.dto.HealthSetUpdateRequest;
import com.simol.ouncommon.healthset.vo.HealthSetCreateResponse;
import com.simol.ouncommon.healthset.vo.HealthSetListResponse;
import com.simol.ouncommon.healthset.vo.HealthSetResponse;

public interface HealthSetService {
    HealthSetCreateResponse createHealthSet(HealthSetCreateRequest healthSetCreateRequest);

    HealthSetResponse getHealthSet(Long healthSetId);

    HealthSetListResponse getHealthSetList(int page, int size, Long healthId);

    HealthSetResponse updateHealthSet(HealthSetUpdateRequest healthSetUpdateRequest);
}
