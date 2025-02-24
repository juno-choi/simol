package com.simol.ouncommon.routine.entity;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RoutineEntityTest {
    @Test
    @DisplayName("루틴 생성 테스트")
    void testCreate() {
        RoutineEntity routineEntity = RoutineEntity.create("test", "test", 1L);
        Assertions.assertThat(routineEntity.getName()).isEqualTo("test");
        Assertions.assertThat(routineEntity.getDescription()).isEqualTo("test");
        Assertions.assertThat(routineEntity.getUserId()).isEqualTo(1L);
    }
}
