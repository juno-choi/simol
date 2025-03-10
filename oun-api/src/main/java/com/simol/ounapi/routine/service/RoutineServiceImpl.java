package com.simol.ounapi.routine.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

import com.simol.ouncommon.routine.service.RoutineService;
import com.simol.ouncommon.auth.entity.UserEntity;
import com.simol.ouncommon.auth.repository.UsersRepository;
import com.simol.ouncommon.exception.BadRequestException;
import com.simol.ouncommon.routine.dto.RoutineCreateRequest;
import com.simol.ouncommon.routine.dto.RoutineUpdateRequest;
import com.simol.ouncommon.routine.entity.RoutineEntity;
import com.simol.ouncommon.routine.repository.RoutineRepository;
import com.simol.ouncommon.routine.vo.RoutineCreateResponse;
import com.simol.ouncommon.routine.vo.RoutineListResponse;
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

        RoutineEntity requestRoutineEntity = RoutineEntity.create(routineCreateRequest, user);
        RoutineEntity routineEntity = routineRepository.save(requestRoutineEntity);

        return RoutineCreateResponse.of(routineEntity);
    }

    @Override
    public RoutineResponse getRoutine(Long routineId) {
        RoutineEntity routineEntity = routineRepository.findById(routineId)
            .orElseThrow(() -> new BadRequestException("Routine not found"));

        return RoutineResponse.of(routineEntity);
    }

    @Override
    public RoutineListResponse getRoutineList(int page, int size, HttpServletRequest request) {
        long userId = Long.parseLong(request.getAttribute("userId").toString());
        Pageable pageable = PageRequest.of(page, size);
        Page<RoutineEntity> routineEntityPage = routineRepository.findAllByPage(pageable, userId);

        return RoutineListResponse.of(routineEntityPage);
    }

    @Override
    @Transactional
    public RoutineResponse updateRoutine(RoutineUpdateRequest routineUpdateRequest, HttpServletRequest request) {
        long userId = Long.parseLong(request.getAttribute("userId").toString());
        RoutineEntity routineEntity = routineRepository.findById(routineUpdateRequest.getRoutineId())
            .orElseThrow(() -> new BadRequestException("Routine not found"));

        if (!routineEntity.getUser().getId().equals(userId)) {
            throw new BadRequestException("Routine not found");
        }
        
        routineEntity.update(routineUpdateRequest);
        // healthList 수정
        routineEntity.updateHealthList(routineUpdateRequest.getHealthList());

        return RoutineResponse.of(routineEntity);
    }
}
