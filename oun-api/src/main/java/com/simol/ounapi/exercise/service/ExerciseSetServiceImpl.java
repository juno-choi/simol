package com.simol.ounapi.exercise.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.simol.simolcommon.common.exception.BadRequestException;
import com.simol.simolcommon.oun.exercise.dto.ExerciseSetCreateRequest;
import com.simol.simolcommon.oun.exercise.dto.ExerciseSetUpdateRequest;
import com.simol.simolcommon.oun.exercise.entity.ExerciseEntity;
import com.simol.simolcommon.oun.exercise.entity.ExerciseSetEntity;
import com.simol.simolcommon.oun.exercise.repository.ExerciseRepository;
import com.simol.simolcommon.oun.exercise.repository.ExerciseSetRepository;
import com.simol.simolcommon.oun.exercise.service.ExerciseSetService;
import com.simol.simolcommon.oun.exercise.vo.ExerciseSetCreateResponse;
import com.simol.simolcommon.oun.exercise.vo.ExerciseSetListResponse;
import com.simol.simolcommon.oun.exercise.vo.ExerciseSetResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ExerciseSetServiceImpl implements ExerciseSetService {
    private final ExerciseRepository exerciseRepository;
    private final ExerciseSetRepository exerciseSetRepository;
    
    @Override
    @Transactional
    public ExerciseSetCreateResponse createExerciseSet(ExerciseSetCreateRequest exerciseSetCreateRequest) {
        ExerciseEntity exercise = exerciseRepository.findById(exerciseSetCreateRequest.getExerciseId())
            .orElseThrow(() -> new BadRequestException("Exercise not found"));

        ExerciseSetEntity exerciseSet = ExerciseSetEntity.create(exerciseSetCreateRequest, exercise);
        ExerciseSetEntity saveExerciseSet = exerciseSetRepository.save(exerciseSet);

        exercise.addExerciseSet(saveExerciseSet);
        return ExerciseSetCreateResponse.of(saveExerciseSet);
    }

    @Override
    public ExerciseSetResponse getExerciseSet(Long exerciseSetId) {
        ExerciseSetEntity exerciseSet = exerciseSetRepository.findById(exerciseSetId)
            .orElseThrow(() -> new BadRequestException("ExerciseSet not found"));

        return ExerciseSetResponse.of(exerciseSet);
    }

    @Override
    public ExerciseSetListResponse getExerciseSetList(int page, int size, Long exerciseId) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ExerciseSetEntity> exerciseSetEntityPage = exerciseSetRepository.findAllByExerciseIdPage(pageable, exerciseId);
        return ExerciseSetListResponse.of(exerciseSetEntityPage);
    }

    @Override
    @Transactional
    public ExerciseSetResponse updateExerciseSet(ExerciseSetUpdateRequest exerciseSetUpdateRequest) {
        ExerciseSetEntity exerciseSet = exerciseSetRepository.findById(exerciseSetUpdateRequest.getExerciseSetId())
            .orElseThrow(() -> new BadRequestException("ExerciseSet not found"));

        exerciseSet.update(exerciseSetUpdateRequest);

        return ExerciseSetResponse.of(exerciseSet);
    }
}
