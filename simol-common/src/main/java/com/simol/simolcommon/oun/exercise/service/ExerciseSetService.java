package com.simol.simolcommon.oun.exercise.service;

import com.simol.simolcommon.oun.exercise.dto.ExerciseSetCreateRequest;
import com.simol.simolcommon.oun.exercise.dto.ExerciseSetUpdateRequest;
import com.simol.simolcommon.oun.exercise.vo.ExerciseSetCreateResponse;
import com.simol.simolcommon.oun.exercise.vo.ExerciseSetListResponse;
import com.simol.simolcommon.oun.exercise.vo.ExerciseSetResponse;

public interface ExerciseSetService {
    ExerciseSetCreateResponse createExerciseSet(ExerciseSetCreateRequest exerciseSetCreateRequest);

    ExerciseSetResponse getExerciseSet(Long exerciseSetId);

    ExerciseSetListResponse getExerciseSetList(int page, int size, Long exerciseId);

    ExerciseSetResponse updateExerciseSet(ExerciseSetUpdateRequest exerciseSetUpdateRequest);
}
