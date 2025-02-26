package com.simol.ounapi.health.service;

import org.springframework.stereotype.Service;

import com.simol.ouncommon.exception.BadRequestException;
import com.simol.ouncommon.health.dto.HealthSetTargetCreateRequest;
import com.simol.ouncommon.health.entity.HealthSetEntity;
import com.simol.ouncommon.health.entity.HealthSetTargetEntity;
import com.simol.ouncommon.health.repository.HealthSetRepository;
import com.simol.ouncommon.health.repository.HealthSetTargetRepository;
import com.simol.ouncommon.health.service.HealthSetTargetService;
import com.simol.ouncommon.health.vo.HealthSetTargetCreateResponse;

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
    
}
