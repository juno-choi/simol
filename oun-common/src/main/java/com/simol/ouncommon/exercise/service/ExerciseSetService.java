package com.simol.ouncommon.exercise.service;

import com.simol.ouncommon.exercise.dto.ExerciseSetCreateRequest;
import com.simol.ouncommon.exercise.dto.ExerciseSetUpdateRequest;
import com.simol.ouncommon.exercise.vo.ExerciseSetCreateResponse;
import com.simol.ouncommon.exercise.vo.ExerciseSetListResponse;
import com.simol.ouncommon.exercise.vo.ExerciseSetResponse;

public interface ExerciseSetService {
    ExerciseSetCreateResponse createExerciseSet(ExerciseSetCreateRequest exerciseSetCreateRequest);

    ExerciseSetResponse getExerciseSet(Long exerciseSetId);

    ExerciseSetListResponse getExerciseSetList(int page, int size, Long exerciseId);

    ExerciseSetResponse updateExerciseSet(ExerciseSetUpdateRequest exerciseSetUpdateRequest);
}
