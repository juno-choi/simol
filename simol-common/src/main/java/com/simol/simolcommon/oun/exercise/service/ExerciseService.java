package com.simol.simolcommon.oun.exercise.service;

import com.simol.simolcommon.oun.exercise.dto.ExerciseCreateRequest;
import com.simol.simolcommon.oun.exercise.dto.ExerciseUpdateRequest;
import com.simol.simolcommon.oun.exercise.vo.ExerciseCreateResponse;
import com.simol.simolcommon.oun.exercise.vo.ExerciseListResponse;
import com.simol.simolcommon.oun.exercise.vo.ExerciseResponse;

import jakarta.servlet.http.HttpServletRequest;

public interface ExerciseService {
    ExerciseCreateResponse createExercise(ExerciseCreateRequest exerciseCreateRequest, HttpServletRequest request);
    ExerciseResponse getExercise(Long exerciseId);
    ExerciseListResponse getExerciseList(Long routineId, int page, int size);
    ExerciseResponse updateExercise(ExerciseUpdateRequest exerciseUpdateRequest, HttpServletRequest request);
    void deleteExercise(Long exerciseId);
}
