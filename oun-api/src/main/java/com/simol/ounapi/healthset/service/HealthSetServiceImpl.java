package com.simol.ounapi.healthset.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.simol.ouncommon.exception.BadRequestException;
import com.simol.ouncommon.health.entity.HealthEntity;
import com.simol.ouncommon.health.repository.HealthRepository;
import com.simol.ouncommon.healthset.service.HealthSetService;
import com.simol.ouncommon.healthset.dto.HealthSetCreateRequest;
import com.simol.ouncommon.healthset.entity.HealthSetEntity;
import com.simol.ouncommon.healthset.repository.HealthSetRepository;
import com.simol.ouncommon.healthset.vo.HealthSetCreateResponse;
import com.simol.ouncommon.healthset.vo.HealthSetListResponse;
import com.simol.ouncommon.healthset.vo.HealthSetResponse;

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

    @Override
    public HealthSetListResponse getHealthSetList(int page, int size, Long healthId) {
        Pageable pageable = PageRequest.of(page, size);
        Page<HealthSetEntity> healthSetEntityPage = healthSetRepository.findAllByHealthIdPage(pageable, healthId);
        return HealthSetListResponse.of(healthSetEntityPage);
    }
}
