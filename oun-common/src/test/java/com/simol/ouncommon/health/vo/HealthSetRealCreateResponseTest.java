package com.simol.ouncommon.health.vo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.simol.ouncommon.health.entity.HealthSetEntity;
import com.simol.ouncommon.health.entity.HealthSetRealEntity;

public class HealthSetRealCreateResponseTest {
    @Test
    @DisplayName("HealthSetRealCreateResponse 생성 테스트")
    void ofSuccess() {
        final Long HEALTH_SET_REAL_ID = 1L;
        final Long HEALTH_SET_ID = 1L;
        final int NUMBER = 1;
        final double WEIGHT = 1.0;
        final double DISTANCE = 1.0;
        final int TIME = 1;
        final double SPEED = 1.0;
        HealthSetEntity healthSet = HealthSetEntity.builder()
                .id(HEALTH_SET_ID)
                .build();

        HealthSetRealEntity healthSetReal = HealthSetRealEntity.builder()
            .id(HEALTH_SET_REAL_ID)
            .healthSet(healthSet)
            .number(NUMBER)
            .weight(WEIGHT)
            .distance(DISTANCE)
            .time(TIME)
            .speed(SPEED)
            .build();

        HealthSetRealCreateResponse healthSetRealCreateResponse = HealthSetRealCreateResponse.of(healthSetReal);

        Assertions.assertThat(healthSetRealCreateResponse.getHealthSetRealId()).isEqualTo(HEALTH_SET_REAL_ID);
        Assertions.assertThat(healthSetRealCreateResponse.getHealthSetId()).isEqualTo(HEALTH_SET_ID);
        Assertions.assertThat(healthSetRealCreateResponse.getNumber()).isEqualTo(NUMBER);
        Assertions.assertThat(healthSetRealCreateResponse.getWeight()).isEqualTo(WEIGHT);
        Assertions.assertThat(healthSetRealCreateResponse.getDistance()).isEqualTo(DISTANCE);
        Assertions.assertThat(healthSetRealCreateResponse.getTime()).isEqualTo(TIME);
        Assertions.assertThat(healthSetRealCreateResponse.getSpeed()).isEqualTo(SPEED);
    }
}
