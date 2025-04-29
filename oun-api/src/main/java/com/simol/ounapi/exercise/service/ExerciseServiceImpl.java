package com.simol.ounapi.exercise.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.simol.simolcommon.common.auth.entity.UserEntity;
import com.simol.simolcommon.common.auth.repository.UsersRepository;
import com.simol.simolcommon.common.exception.BadRequestException;
import com.simol.simolcommon.oun.exercise.dto.ExerciseCreateRequest;
import com.simol.simolcommon.oun.exercise.dto.ExerciseUpdateRequest;
import com.simol.simolcommon.oun.exercise.entity.ExerciseEntity;
import com.simol.simolcommon.oun.exercise.repository.ExerciseRepository;
import com.simol.simolcommon.oun.exercise.service.ExerciseService;
import com.simol.simolcommon.oun.exercise.vo.ExerciseCreateResponse;
import com.simol.simolcommon.oun.exercise.vo.ExerciseListResponse;
import com.simol.simolcommon.oun.exercise.vo.ExerciseResponse;
import com.simol.simolcommon.oun.routine.entity.RoutineEntity;
import com.simol.simolcommon.oun.routine.repository.RoutineRepository;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ExerciseServiceImpl implements ExerciseService {
    private final UsersRepository usersRepository;
    private final RoutineRepository routineRepository;
    private final ExerciseRepository exerciseRepository;

    @Override
    @Transactional
    public ExerciseCreateResponse createExercise(ExerciseCreateRequest exerciseCreateRequest, HttpServletRequest request) {
        long userId = Long.parseLong(request.getAttribute("userId").toString());
        UserEntity user = usersRepository.findById(userId)
            .orElseThrow(() -> new BadRequestException("User not found"));
        
        // routine 찾기
        long routineId = exerciseCreateRequest.getRoutineId();
        RoutineEntity routineEntity = routineRepository.findById(routineId)
            .orElseThrow(() -> new BadRequestException("Routine not found"));
        ExerciseEntity exerciseEntity = ExerciseEntity.create(exerciseCreateRequest, routineEntity, user);
        ExerciseEntity saveExercise = exerciseRepository.save(exerciseEntity);
        // 루틴에 추가
        routineEntity.addExercise(exerciseEntity);
        
        return ExerciseCreateResponse.of(saveExercise);
    }

    @Override
    public ExerciseResponse getExercise(Long exerciseId) {
        ExerciseEntity exerciseEntity = exerciseRepository.findById(exerciseId)
            .orElseThrow(() -> new BadRequestException("Exercise not found"));
        return ExerciseResponse.of(exerciseEntity);
    }

    @Override
    public ExerciseListResponse getExerciseList(Long routineId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ExerciseEntity> exercisePage = exerciseRepository.findAllByPage(pageable, routineId);
        return ExerciseListResponse.of(exercisePage);
    }

    @Override
    @Transactional
    public ExerciseResponse updateExercise(ExerciseUpdateRequest exerciseUpdateRequest, HttpServletRequest request) {
        ExerciseEntity exerciseEntity = exerciseRepository.findById(exerciseUpdateRequest.getExerciseId())
            .orElseThrow(() -> new BadRequestException("Exercise not found"));
        
        exerciseEntity.update(exerciseUpdateRequest);

        // exerciseSetList 수정
        exerciseEntity.updateExerciseSetList(exerciseUpdateRequest.getExerciseSetList());

        return ExerciseResponse.of(exerciseEntity);
    }

    @Transactional
    @Override
    public void deleteExercise(Long exerciseId) {
        
        ExerciseEntity exerciseEntity = exerciseRepository.findById(exerciseId)
            .orElseThrow(() -> new BadRequestException("Exercise not found"));

        exerciseEntity.delete();
    }
    
}
