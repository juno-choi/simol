package com.simol.ounapi.fixture;

import java.util.ArrayList;

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
}
