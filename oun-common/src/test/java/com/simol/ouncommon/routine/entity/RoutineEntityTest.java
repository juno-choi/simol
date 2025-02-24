package com.simol.ouncommon.routine.entity;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.simol.ouncommon.auth.entity.UserEntity;

public class RoutineEntityTest {
    @Test
    @DisplayName("루틴 생성 테스트")
    void testCreate() {
        UserEntity user = UserEntity.create("test@test.com", "test", "test", "test");
        RoutineEntity routineEntity = RoutineEntity.create("test", "test", user);
        Assertions.assertThat(routineEntity.getName()).isEqualTo("test");
        Assertions.assertThat(routineEntity.getDescription()).isEqualTo("test");
        Assertions.assertThat(routineEntity.getUser()).isNotNull();
    }
}
