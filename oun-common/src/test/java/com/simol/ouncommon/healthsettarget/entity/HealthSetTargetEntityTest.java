package com.simol.ouncommon.healthsettarget.entity;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.simol.ouncommon.healthset.entity.HealthSetEntity;
import com.simol.ouncommon.healthset.enums.HealthSetStatus;
import com.simol.ouncommon.healthsettarget.dto.HealthSetTargetCreateRequest;

public class HealthSetTargetEntityTest {
    @Test
    @DisplayName("HealthSetTargetEntity 생성 테스트")
    void createSuccess() {
        final int NUMBER = 1;
        final double WEIGHT = 1.0;
        final double DISTANCE = 1.0;
        final int TIME = 1;
        final double SPEED = 1.0;

        HealthSetTargetCreateRequest healthSetTargetCreateRequest = HealthSetTargetCreateRequest.builder()
            .number(NUMBER)
            .weight(WEIGHT)
            .distance(DISTANCE)
            .time(TIME)
            .speed(SPEED)
                .build();

        HealthSetEntity healthSet = HealthSetEntity.builder()
            .id(1L)
            .description("test")
            .sort(1)
            .status(HealthSetStatus.ACTIVE)
            .build();

        HealthSetTargetEntity healthSetTarget = HealthSetTargetEntity.create(healthSetTargetCreateRequest, healthSet);

        Assertions.assertThat(healthSetTarget.getHealthSet()).isEqualTo(healthSet);
        Assertions.assertThat(healthSetTarget.getNumber()).isEqualTo(NUMBER);
        Assertions.assertThat(healthSetTarget.getWeight()).isEqualTo(WEIGHT);
        Assertions.assertThat(healthSetTarget.getDistance()).isEqualTo(DISTANCE);
        Assertions.assertThat(healthSetTarget.getTime()).isEqualTo(TIME);
        Assertions.assertThat(healthSetTarget.getSpeed()).isEqualTo(SPEED);
    }
}
