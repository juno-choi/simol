package com.simol.ouncommon.health.vo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.simol.ouncommon.health.entity.HealthSetTargetEntity;

public class HealthSetTargetResponseTest {
    @Test
    @DisplayName("HealthSetTargetResponse 생성 테스트")
    void ofSuccess() {
        final Long HEALTH_SET_TARGET_ID = 1L;
        final int NUMBER = 1;
        final double WEIGHT = 1.0;
        final double DISTANCE = 1.0;
        final int TIME = 1;
        final double SPEED = 1.0;

        HealthSetTargetEntity healthSetTarget = HealthSetTargetEntity.builder()
            .id(HEALTH_SET_TARGET_ID)
            .number(NUMBER)
            .weight(WEIGHT)
            .distance(DISTANCE)
            .time(TIME)
            .speed(SPEED)
            .build();

        HealthSetTargetResponse healthSetTargetResponse = HealthSetTargetResponse.of(healthSetTarget);

        Assertions.assertThat(healthSetTargetResponse.getHealthSetTargetId()).isEqualTo(HEALTH_SET_TARGET_ID);
        Assertions.assertThat(healthSetTargetResponse.getNumber()).isEqualTo(NUMBER);

    }
}
