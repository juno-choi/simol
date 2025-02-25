package com.simol.ouncommon.routine.vo;

import java.time.LocalDateTime;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import com.simol.ouncommon.routine.entity.RoutineEntity;
import com.simol.ouncommon.routine.enums.RoutineStatus;

public class RoutineResponseTest {
    @Test
    void ofSuccess() {
        RoutineEntity routine = RoutineEntity.builder()
            .id(1L)
            .name("test")
            .description("test")
            .status(RoutineStatus.ACTIVE)
            .build();

        RoutineResponse response = RoutineResponse.of(routine);

        Assertions.assertThat(response.getRoutineId()).isEqualTo(1L);
        Assertions.assertThat(response.getName()).isEqualTo("test");
        Assertions.assertThat(response.getDescription()).isEqualTo("test");
    }
}
