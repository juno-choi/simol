package com.simol.ounapi.exercise.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.simol.ouncommon.exception.BadRequestException;
import com.simol.ouncommon.exercise.dto.ExerciseSetCreateRequest;
import com.simol.ouncommon.exercise.dto.ExerciseSetUpdateRequest;
import com.simol.ouncommon.exercise.entity.ExerciseEntity;
import com.simol.ouncommon.exercise.entity.ExerciseSetEntity;
import com.simol.ouncommon.exercise.repository.ExerciseRepository;
import com.simol.ouncommon.exercise.repository.ExerciseSetRepository;
import com.simol.ouncommon.exercise.service.ExerciseSetService;
import com.simol.ouncommon.exercise.vo.ExerciseSetCreateResponse;
import com.simol.ouncommon.exercise.vo.ExerciseSetListResponse;
import com.simol.ouncommon.exercise.vo.ExerciseSetResponse;

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
