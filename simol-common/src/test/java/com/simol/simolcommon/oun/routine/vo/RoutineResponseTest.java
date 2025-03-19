package com.simol.simolcommon.oun.routine.vo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import com.simol.simolcommon.oun.routine.entity.RoutineEntity;
import com.simol.simolcommon.oun.routine.enums.RoutineStatus;
import com.simol.simolcommon.oun.routine.vo.RoutineResponse;

public class RoutineResponseTest {
    @Test
    void ofSuccess() {
        RoutineEntity routine = RoutineEntity.builder()
            .name("test")
            .description("test")
            .status(RoutineStatus.ACTIVE)
            .build();

        RoutineResponse response = RoutineResponse.of(routine);

        Assertions.assertThat(response.getName()).isEqualTo("test");
        Assertions.assertThat(response.getDescription()).isEqualTo("test");
    }
}
