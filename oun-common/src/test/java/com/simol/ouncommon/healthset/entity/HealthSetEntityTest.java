package com.simol.ouncommon.healthset.entity;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.simol.ouncommon.auth.entity.UserEntity;
import com.simol.ouncommon.health.entity.HealthEntity;
import com.simol.ouncommon.health.enums.HealthStatus;
import com.simol.ouncommon.healthset.dto.HealthSetCreateRequest;
import com.simol.ouncommon.healthset.enums.HealthSetType;
import com.simol.ouncommon.routine.entity.RoutineEntity;

public class HealthSetEntityTest {
    @Test
    @DisplayName("HealthSetEntity 생성 테스트")
    void createSuccess() {
        HealthSetCreateRequest healthSetCreateRequest = HealthSetCreateRequest.builder()
            .description("test")
            .healthSetType(HealthSetType.WEIGHT)
            .setNumber(1)
            .setCount(10)
            .setWeight(100)
            .setDistance(1000)
            .setTime(100)
            .setSpeed(10)
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
        Assertions.assertThat(healthSet.getHealthSetType()).isEqualTo(HealthSetType.WEIGHT);
        Assertions.assertThat(healthSet.getSetNumber()).isEqualTo(1);
        Assertions.assertThat(healthSet.getSetCount()).isEqualTo(10);
        Assertions.assertThat(healthSet.getSetWeight()).isEqualTo(100);
        Assertions.assertThat(healthSet.getSetDistance()).isEqualTo(1000);
        Assertions.assertThat(healthSet.getSetTime()).isEqualTo(100);
    }
}
