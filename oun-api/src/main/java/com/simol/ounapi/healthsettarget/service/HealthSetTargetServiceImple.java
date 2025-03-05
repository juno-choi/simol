package com.simol.ounapi.healthsettarget.service;

import org.springframework.stereotype.Service;

import com.simol.ouncommon.exception.BadRequestException;
import com.simol.ouncommon.healthset.entity.HealthSetEntity;
import com.simol.ouncommon.healthset.repository.HealthSetRepository;
import com.simol.ouncommon.healthsettarget.dto.HealthSetTargetCreateRequest;
import com.simol.ouncommon.healthsettarget.entity.HealthSetTargetEntity;
import com.simol.ouncommon.healthsettarget.repository.HealthSetTargetRepository;
import com.simol.ouncommon.healthsettarget.service.HealthSetTargetService;
import com.simol.ouncommon.healthsettarget.vo.HealthSetTargetCreateResponse;
import com.simol.ouncommon.healthsettarget.vo.HealthSetTargetResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HealthSetTargetServiceImple implements HealthSetTargetService{
    private final HealthSetTargetRepository healthSetTargetRepository;
    private final HealthSetRepository healthSetRepository;
    @Override
    public HealthSetTargetCreateResponse createHealthSetTarget(HealthSetTargetCreateRequest healthSetTargetCreateRequest) {

        HealthSetEntity healthSet = healthSetRepository.findById(healthSetTargetCreateRequest.getHealthSetId())
            .orElseThrow(() -> new BadRequestException("HealthSet not found"));

        HealthSetTargetEntity healthSetTarget = HealthSetTargetEntity.create(healthSetTargetCreateRequest, healthSet);
        HealthSetTargetEntity savedHealthSetTarget = healthSetTargetRepository.save(healthSetTarget);

        return HealthSetTargetCreateResponse.of(savedHealthSetTarget);
    }
    @Override
    public HealthSetTargetResponse getHealthSetTarget(Long healthSetTargetId) {
        HealthSetTargetEntity healthSetTarget = healthSetTargetRepository.findById(healthSetTargetId)
            .orElseThrow(() -> new BadRequestException("HealthSetTarget not found"));

        return HealthSetTargetResponse.of(healthSetTarget);
    }
    
}
