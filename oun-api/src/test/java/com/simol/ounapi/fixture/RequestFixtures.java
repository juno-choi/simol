package com.simol.ounapi.fixture;

import java.util.ArrayList;

import com.simol.ouncommon.exercise.dto.ExerciseCreateRequest;
import com.simol.ouncommon.exercise.dto.ExerciseUpdateRequest;
import com.simol.ouncommon.exercise.enums.ExerciseType;
import com.simol.ouncommon.routine.dto.RoutineCreateRequest;
import com.simol.ouncommon.routine.dto.RoutineUpdateRequest;
import com.simol.ouncommon.routine.enums.RoutineDays;

public class RequestFixtures {
    public static RoutineCreateRequest.RoutineCreateRequestBuilder aRoutineCreateRequest() {
        return RoutineCreateRequest.builder()
            .name("test")
            .description("test")
            .days(RoutineDays.MONDAY)
            ;
    }

    public static RoutineUpdateRequest.RoutineUpdateRequestBuilder aRoutineUpdateRequest() {
        return RoutineUpdateRequest.builder()
            .routineId(1L)
            .name("test")
            .description("test")
            .days(RoutineDays.MONDAY)
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
}
