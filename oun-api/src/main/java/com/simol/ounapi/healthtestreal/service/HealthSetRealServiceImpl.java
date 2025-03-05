package com.simol.ounapi.healthtestreal.service;

import org.springframework.stereotype.Service;

import com.simol.ouncommon.healthset.entity.HealthSetEntity;
import com.simol.ouncommon.healthset.repository.HealthSetRepository;
import com.simol.ouncommon.healthsetreal.dto.HealthSetRealCreateRequest;
import com.simol.ouncommon.healthsetreal.entity.HealthSetRealEntity;
import com.simol.ouncommon.healthsetreal.repository.HealthSetRealRepository;
import com.simol.ouncommon.healthsetreal.service.HealthSetRealService;
import com.simol.ouncommon.healthsetreal.vo.HealthSetRealCreateResponse;
import com.simol.ouncommon.healthsetreal.vo.HealthSetRealResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HealthSetRealServiceImpl implements HealthSetRealService {
    private final HealthSetRealRepository healthSetRealRepository;
    private final HealthSetRepository healthSetRepository;
    @Override
    public HealthSetRealCreateResponse createHealthSetReal(HealthSetRealCreateRequest healthSetRealCreateRequest) {
        HealthSetEntity healthSet = healthSetRepository.findById(healthSetRealCreateRequest.getHealthSetId())
            .orElseThrow(() -> new RuntimeException("HealthSet not found"));

        HealthSetRealEntity healthSetReal = HealthSetRealEntity.create(healthSetRealCreateRequest, healthSet);
        HealthSetRealEntity savedHealthSetReal = healthSetRealRepository.save(healthSetReal);

        return HealthSetRealCreateResponse.of(savedHealthSetReal);
    }
    @Override
    public HealthSetRealResponse getHealthSetReal(Long healthSetRealId) {
        HealthSetRealEntity healthSetReal = healthSetRealRepository.findById(healthSetRealId)
            .orElseThrow(() -> new RuntimeException("HealthSetReal not found"));

        return HealthSetRealResponse.of(healthSetReal);
    }
}
