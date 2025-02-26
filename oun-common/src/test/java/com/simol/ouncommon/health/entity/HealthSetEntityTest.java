package com.simol.ouncommon.health.entity;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.simol.ouncommon.auth.entity.UserEntity;
import com.simol.ouncommon.health.dto.HealthSetCreateRequest;
import com.simol.ouncommon.health.enums.HealthStatus;
import com.simol.ouncommon.routine.entity.RoutineEntity;

public class HealthSetEntityTest {
    @Test
    @DisplayName("HealthSetEntity 생성 테스트")
    void createSuccess() {
        HealthSetCreateRequest healthSetCreateRequest = HealthSetCreateRequest.builder()
            .description("test")
            .sort(1)
            .build();

        UserEntity user = UserEntity.create("test@test.com", "test", "test", "test");
        RoutineEntity routine = RoutineEntity.create("test", "test", user);
        HealthEntity health = HealthEntity.builder()
            .id(1L)
            .routine(routine)
            .name("test")
            .description("test")
            .sort(1)
            .status(HealthStatus.ACTIVE)
            .build();
        
        HealthSetEntity healthSet = HealthSetEntity.create(healthSetCreateRequest, health);

        Assertions.assertThat(healthSet.getHealth()).isEqualTo(health);
        Assertions.assertThat(healthSet.getDescription()).isEqualTo("test");
        Assertions.assertThat(healthSet.getSort()).isEqualTo(1);
    }
}
