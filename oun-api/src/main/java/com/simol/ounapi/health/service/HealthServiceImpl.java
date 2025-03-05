package com.simol.ounapi.health.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.simol.ouncommon.auth.entity.UserEntity;
import com.simol.ouncommon.auth.repository.UsersRepository;
import com.simol.ouncommon.exception.BadRequestException;
import com.simol.ouncommon.health.dto.HealthCreateRequest;
import com.simol.ouncommon.health.dto.HealthUpdateRequest;
import com.simol.ouncommon.health.entity.HealthEntity;
import com.simol.ouncommon.health.repository.HealthRepository;
import com.simol.ouncommon.health.service.HealthService;
import com.simol.ouncommon.health.vo.HealthCreateResponse;
import com.simol.ouncommon.health.vo.HealthListResponse;
import com.simol.ouncommon.health.vo.HealthResponse;
import com.simol.ouncommon.routine.entity.RoutineEntity;
import com.simol.ouncommon.routine.repository.RoutineRepository;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class HealthServiceImpl implements HealthService {
    private final UsersRepository usersRepository;
    private final RoutineRepository routineRepository;
    private final HealthRepository healthRepository;

    @Override
    @Transactional
    public HealthCreateResponse createHealth(HealthCreateRequest healthCreateRequest, HttpServletRequest request) {
        long userId = Long.parseLong(request.getAttribute("userId").toString());
        UserEntity user = usersRepository.findById(userId)
            .orElseThrow(() -> new BadRequestException("User not found"));
        
        // routine 찾기
        long routineId = healthCreateRequest.getRoutineId();
        RoutineEntity routineEntity = routineRepository.findById(routineId)
            .orElseThrow(() -> new BadRequestException("Routine not found"));
        HealthEntity healthEntity = HealthEntity.create(healthCreateRequest, routineEntity, user);
        HealthEntity saveHealth = healthRepository.save(healthEntity);

        return HealthCreateResponse.of(saveHealth);
    }

    @Override
    public HealthResponse getHealth(Long healthId) {
        HealthEntity healthEntity = healthRepository.findById(healthId)
            .orElseThrow(() -> new BadRequestException("Health not found"));
        return HealthResponse.of(healthEntity);
    }

    @Override
    public HealthListResponse getHealthList(Long routineId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<HealthEntity> healthPage = healthRepository.findAllByPage(pageable, routineId);
        return HealthListResponse.of(healthPage);
    }

    @Override
    @Transactional
    public HealthResponse updateHealth(HealthUpdateRequest healthUpdateRequest, HttpServletRequest request) {
        HealthEntity healthEntity = healthRepository.findById(healthUpdateRequest.getHealthId())
            .orElseThrow(() -> new BadRequestException("Health not found"));
        
        healthEntity.update(healthUpdateRequest);
        HealthEntity saveHealth = healthRepository.save(healthEntity);
        return HealthResponse.of(saveHealth);
    }
    
}
