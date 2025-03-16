package com.simol.ounapi.fixture;

import com.simol.ouncommon.routine.dto.RoutineCreateRequest;
import com.simol.ouncommon.routine.enums.RoutineDays;

public class RequestFixtures {
    public static RoutineCreateRequest.RoutineCreateRequestBuilder aRoutineCreateRequest() {
        return RoutineCreateRequest.builder()
            .name("test")
            .description("test")
            .days(RoutineDays.MONDAY)
            ;
    }
}
