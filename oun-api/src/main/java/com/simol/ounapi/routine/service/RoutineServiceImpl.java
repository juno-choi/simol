package com.simol.ounapi.routine.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

import com.simol.ouncommon.routine.service.RoutineService;
import com.simol.ouncommon.auth.entity.UserEntity;
import com.simol.ouncommon.auth.repository.UsersRepository;
import com.simol.ouncommon.exception.BadRequestException;
import com.simol.ouncommon.routine.dto.RoutineCreateRequest;
import com.simol.ouncommon.routine.entity.RoutineEntity;
import com.simol.ouncommon.routine.repository.RoutineRepository;
import com.simol.ouncommon.routine.vo.RoutineCreateResponse;
import com.simol.ouncommon.routine.vo.RoutineResponse;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RoutineServiceImpl implements RoutineService {
    private final RoutineRepository routineRepository;
    private final UsersRepository usersRepository;
    
    @Override
    @Transactional
    public RoutineCreateResponse createRoutine(RoutineCreateRequest routineCreateRequest, HttpServletRequest request) {
        long userId = Long.parseLong(request.getAttribute("userId").toString());
        
        UserEntity user = usersRepository.findById(userId)
            .orElseThrow(() -> new BadRequestException("User not found"));

        RoutineEntity requestRoutineEntity = RoutineEntity.create(routineCreateRequest.getName(), routineCreateRequest.getDescription(), user);
        RoutineEntity routineEntity = routineRepository.save(requestRoutineEntity);

        return RoutineCreateResponse.builder()
            .routineId(routineEntity.getId())
            .name(routineEntity.getName())
            .description(routineEntity.getDescription())
            .status(routineEntity.getStatus())
            .createdAt(routineEntity.getCreatedAt())
            .updatedAt(routineEntity.getUpdatedAt())
            .build();
    }

    @Override
    public RoutineResponse getRoutine(Long routineId) {
        RoutineEntity routineEntity = routineRepository.findById(routineId)
            .orElseThrow(() -> new BadRequestException("Routine not found"));

        return RoutineResponse.of(routineEntity);
    }
}
