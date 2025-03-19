package com.simol.ounapi.fixture;

import java.util.ArrayList;

import com.simol.simolcommon.exercise.dto.ExerciseCreateRequest;
import com.simol.simolcommon.exercise.dto.ExerciseSetCreateRequest;
import com.simol.simolcommon.exercise.dto.ExerciseUpdateRequest;
import com.simol.simolcommon.exercise.enums.ExerciseType;
import com.simol.simolcommon.routine.dto.RoutineCreateRequest;
import com.simol.simolcommon.routine.dto.RoutineUpdateRequest;

public class RequestFixtures {
    public static RoutineCreateRequest.RoutineCreateRequestBuilder aRoutineCreateRequest() {
        return RoutineCreateRequest.builder()
            .name("test")
            .description("test")
            ;
    }

    public static RoutineUpdateRequest.RoutineUpdateRequestBuilder aRoutineUpdateRequest() {
        return RoutineUpdateRequest.builder()
            .routineId(1L)
            .name("test")
            .description("test")
            .exerciseList(new ArrayList<>())
            ;
    }

    public static ExerciseCreateRequest.ExerciseCreateRequestBuilder aExerciseCreateRequest() {
        return ExerciseCreateRequest.builder()
            .routineId(1L)
            .name("test")
            .description("test")
            .sort(1)
            .type(ExerciseType.WEIGHT)
            ;
    }

    public static ExerciseUpdateRequest.ExerciseUpdateRequestBuilder aExerciseUpdateRequest() {
        return ExerciseUpdateRequest.builder()
            .exerciseId(1L)
            .name("test")
            .description("test")
            .sort(1)
            .exerciseSetList(new ArrayList<>())
            ;
    }

    public static ExerciseSetCreateRequest.ExerciseSetCreateRequestBuilder aExerciseSetCreateRequest() {
        return ExerciseSetCreateRequest.builder()
            .exerciseId(1L)
            .number(1)
            .weight(100)
            .count(0)
            .distance(0)
            .time(0)
            .speed(0)
            .description("")
            ;
    }
}
