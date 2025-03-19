package com.simol.simolcommon.exercise.service;

import com.simol.simolcommon.exercise.dto.ExerciseSetCreateRequest;
import com.simol.simolcommon.exercise.dto.ExerciseSetUpdateRequest;
import com.simol.simolcommon.exercise.vo.ExerciseSetCreateResponse;
import com.simol.simolcommon.exercise.vo.ExerciseSetListResponse;
import com.simol.simolcommon.exercise.vo.ExerciseSetResponse;

public interface ExerciseSetService {
    ExerciseSetCreateResponse createExerciseSet(ExerciseSetCreateRequest exerciseSetCreateRequest);

    ExerciseSetResponse getExerciseSet(Long exerciseSetId);

    ExerciseSetListResponse getExerciseSetList(int page, int size, Long exerciseId);

    ExerciseSetResponse updateExerciseSet(ExerciseSetUpdateRequest exerciseSetUpdateRequest);
}
