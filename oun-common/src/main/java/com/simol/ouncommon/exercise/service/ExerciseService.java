package com.simol.ouncommon.exercise.service;

import com.simol.ouncommon.exercise.dto.ExerciseCreateRequest;
import com.simol.ouncommon.exercise.dto.ExerciseUpdateRequest;
import com.simol.ouncommon.exercise.vo.ExerciseCreateResponse;
import com.simol.ouncommon.exercise.vo.ExerciseListResponse;
import com.simol.ouncommon.exercise.vo.ExerciseResponse;

import jakarta.servlet.http.HttpServletRequest;

public interface ExerciseService {
    ExerciseCreateResponse createExercise(ExerciseCreateRequest exerciseCreateRequest, HttpServletRequest request);
    ExerciseResponse getExercise(Long exerciseId);
    ExerciseListResponse getExerciseList(Long routineId, int page, int size);
    ExerciseResponse updateExercise(ExerciseUpdateRequest exerciseUpdateRequest, HttpServletRequest request);
    void deleteExercise(Long exerciseId);
}
