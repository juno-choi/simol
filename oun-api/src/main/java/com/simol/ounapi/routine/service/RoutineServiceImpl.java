package com.simol.ounapi.routine.service;

import org.springframework.stereotype.Service;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

import com.simol.ouncommon.routine.service.RoutineService;
import com.simol.ouncommon.routine.dto.RoutineCreateRequest;
import com.simol.ouncommon.routine.entity.RoutineEntity;
import com.simol.ouncommon.routine.repository.RoutineRepository;
import com.simol.ouncommon.routine.vo.RoutineCreateResponse;

@Service
@RequiredArgsConstructor
public class RoutineServiceImpl implements RoutineService {
    private final RoutineRepository routineRepository;
    
    @Override
    public RoutineCreateResponse createRoutine(RoutineCreateRequest routineCreateRequest, HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        // todo userId로 user를 조회하여 routine에 넣어주기

        RoutineEntity requestRoutineEntity = RoutineEntity.create(routineCreateRequest.getName(), routineCreateRequest.getDescription(), userId);
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
}
