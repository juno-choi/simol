package com.simol.ounapi.health.service;

import org.springframework.stereotype.Service;

import com.simol.ouncommon.health.dto.HealthSetRealCreateRequest;
import com.simol.ouncommon.health.entity.HealthSetEntity;
import com.simol.ouncommon.health.entity.HealthSetRealEntity;
import com.simol.ouncommon.health.repository.HealthSetRealRepository;
import com.simol.ouncommon.health.repository.HealthSetRepository;
import com.simol.ouncommon.health.service.HealthSetRealService;
import com.simol.ouncommon.health.vo.HealthSetRealCreateResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HealthSetRealServiceImpl implements HealthSetRealService {
    private final HealthSetRealRepository healthSetRealRepository;
    private final HealthSetRepository healthSetRepository;
    @Override
    public HealthSetRealCreateResponse create(HealthSetRealCreateRequest healthSetRealCreateRequest) {
        HealthSetEntity healthSet = healthSetRepository.findById(healthSetRealCreateRequest.getHealthSetId())
            .orElseThrow(() -> new RuntimeException("HealthSet not found"));

        HealthSetRealEntity healthSetReal = HealthSetRealEntity.create(healthSetRealCreateRequest, healthSet);
        HealthSetRealEntity savedHealthSetReal = healthSetRealRepository.save(healthSetReal);

        return HealthSetRealCreateResponse.of(savedHealthSetReal);
    }
}
