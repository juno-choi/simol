package com.simol.ounapi.routine.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

import com.simol.simolcommon.common.auth.entity.UserEntity;
import com.simol.simolcommon.common.auth.repository.UsersRepository;
import com.simol.simolcommon.common.exception.BadRequestException;
import com.simol.simolcommon.oun.routine.dto.RoutineCreateRequest;
import com.simol.simolcommon.oun.routine.dto.RoutineUpdateRequest;
import com.simol.simolcommon.oun.routine.entity.RoutineEntity;
import com.simol.simolcommon.oun.routine.repository.RoutineRepository;
import com.simol.simolcommon.oun.routine.service.RoutineService;
import com.simol.simolcommon.oun.routine.vo.RoutineCreateResponse;
import com.simol.simolcommon.oun.routine.vo.RoutineListResponse;
import com.simol.simolcommon.oun.routine.vo.RoutineResponse;

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
            throw new BadRequestException("User Routine not found");
        }
        
        routineEntity.update(routineUpdateRequest);
        // exerciseList 수정
        routineEntity.updateExerciseList(routineUpdateRequest.getExerciseList());

        return RoutineResponse.of(routineEntity);
    }

    @Transactional
    @Override
    public void deleteRoutine(Long routineId, HttpServletRequest request) {
        long userId = Long.parseLong(request.getAttribute("userId").toString());
        RoutineEntity routineEntity = routineRepository.findById(routineId)
            .orElseThrow(() -> new BadRequestException("Routine not found"));
        
        if (!routineEntity.getUser().getId().equals(userId)) {
            throw new BadRequestException("User Routine not found");
        }

        routineEntity.delete();
    }
}
