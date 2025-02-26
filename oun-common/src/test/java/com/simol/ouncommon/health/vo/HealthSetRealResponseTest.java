package com.simol.ouncommon.health.vo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.simol.ouncommon.health.entity.HealthSetRealEntity;

public class HealthSetRealResponseTest {
    @Test
    @DisplayName("HealthSetRealResponse 생성 테스트")
    void ofSuccess() {
        final Long HEALTH_SET_REAL_ID = 1L;
        final int NUMBER = 1;
        final double WEIGHT = 1.0;
        final double DISTANCE = 1.0;
        final int TIME = 1;
        final double SPEED = 1.0;

        HealthSetRealEntity healthSetReal = HealthSetRealEntity.builder()
            .id(HEALTH_SET_REAL_ID)
            .number(NUMBER)
            .weight(WEIGHT)
            .distance(DISTANCE)
            .time(TIME)
            .speed(SPEED)
            .build();

        HealthSetRealResponse healthSetRealResponse = HealthSetRealResponse.of(healthSetReal);

        Assertions.assertThat(healthSetRealResponse.getHealthSetRealId()).isEqualTo(HEALTH_SET_REAL_ID);
        Assertions.assertThat(healthSetRealResponse.getNumber()).isEqualTo(NUMBER);
        Assertions.assertThat(healthSetRealResponse.getWeight()).isEqualTo(WEIGHT);
        Assertions.assertThat(healthSetRealResponse.getDistance()).isEqualTo(DISTANCE);
        Assertions.assertThat(healthSetRealResponse.getTime()).isEqualTo(TIME);
        Assertions.assertThat(healthSetRealResponse.getSpeed()).isEqualTo(SPEED);
    }
}
