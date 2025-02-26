package com.simol.ounapi.health.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.simol.ouncommon.exception.BadRequestException;
import com.simol.ouncommon.health.dto.HealthSetCreateRequest;
import com.simol.ouncommon.health.entity.HealthSetEntity;
import com.simol.ouncommon.health.entity.HealthEntity;
import com.simol.ouncommon.health.repository.HealthSetRepository;
import com.simol.ouncommon.health.repository.HealthRepository;
import com.simol.ouncommon.health.service.HealthSetService;
import com.simol.ouncommon.health.vo.HealthSetCreateResponse;
import com.simol.ouncommon.health.vo.HealthSetResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class HealthSetServiceImpl implements HealthSetService {
    private final HealthRepository healthRepository;
    private final HealthSetRepository healthSetRepository;
    
    @Override
    @Transactional
    public HealthSetCreateResponse createHealthSet(HealthSetCreateRequest healthDetailCreateRequest) {
        HealthEntity health = healthRepository.findById(healthDetailCreateRequest.getHealthId())
            .orElseThrow(() -> new BadRequestException("Health not found"));

        HealthSetEntity healthDetail = HealthSetEntity.create(healthDetailCreateRequest, health);
        HealthSetEntity saveHealthDetail = healthSetRepository.save(healthDetail);

        return HealthSetCreateResponse.of(saveHealthDetail);
    }

    @Override
    public HealthSetResponse getHealthSet(Long healthSetId) {
        HealthSetEntity healthSet = healthSetRepository.findById(healthSetId)
            .orElseThrow(() -> new BadRequestException("HealthSet not found"));

        return HealthSetResponse.of(healthSet);
    }
}
