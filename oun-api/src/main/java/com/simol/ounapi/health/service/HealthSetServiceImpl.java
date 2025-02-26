package com.simol.ounapi.health.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.simol.ouncommon.auth.entity.UserEntity;
import com.simol.ouncommon.auth.repository.UsersRepository;
import com.simol.ouncommon.exception.BadRequestException;
import com.simol.ouncommon.health.dto.HealthSetCreateRequest;
import com.simol.ouncommon.health.entity.HealthSetEntity;
import com.simol.ouncommon.health.entity.HealthEntity;
import com.simol.ouncommon.health.repository.HealthDetailRepository;
import com.simol.ouncommon.health.repository.HealthRepository;
import com.simol.ouncommon.health.service.HealthDetailService;
import com.simol.ouncommon.health.vo.HealthSetCreateResponse;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class HealthSetServiceImpl implements HealthDetailService {
    private final UsersRepository usersRepository;
    private final HealthRepository healthRepository;
    private final HealthDetailRepository healthDetailRepository;
    
    @Override
    @Transactional
    public HealthSetCreateResponse createHealthDetail(HealthSetCreateRequest healthDetailCreateRequest, HttpServletRequest request) {
        long userId = Long.parseLong(request.getAttribute("userId").toString());

        UserEntity user = usersRepository.findById(userId)
            .orElseThrow(() -> new BadRequestException("User not found"));

        HealthEntity health = healthRepository.findById(healthDetailCreateRequest.getHealthId())
            .orElseThrow(() -> new BadRequestException("Health not found"));

        HealthSetEntity healthDetail = HealthSetEntity.create(healthDetailCreateRequest, health, user);
        HealthSetEntity saveHealthDetail = healthDetailRepository.save(healthDetail);

        return HealthSetCreateResponse.of(saveHealthDetail);
    }
}
