package com.simol.ouncommon.health.entity;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.simol.ouncommon.health.dto.HealthSetRealCreateRequest;
import com.simol.ouncommon.health.enums.HealthSetStatus;

public class HealthSetRealEntityTest {
    @Test
    @DisplayName("HealthSetRealEntity 생성 테스트")
    void createSuccess() {
        final int NUMBER = 1;
        final double WEIGHT = 1.0;
        final double DISTANCE = 1.0;
        final int TIME = 1;
        final double SPEED = 1.0;

        HealthSetRealCreateRequest healthSetRealCreateRequest = HealthSetRealCreateRequest.builder()
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

        HealthSetRealEntity healthSetReal = HealthSetRealEntity.create(healthSetRealCreateRequest, healthSet);

        Assertions.assertThat(healthSetReal.getHealthSet()).isEqualTo(healthSet);
        Assertions.assertThat(healthSetReal.getNumber()).isEqualTo(NUMBER);
        Assertions.assertThat(healthSetReal.getWeight()).isEqualTo(WEIGHT);
        Assertions.assertThat(healthSetReal.getDistance()).isEqualTo(DISTANCE);
        Assertions.assertThat(healthSetReal.getTime()).isEqualTo(TIME);
        Assertions.assertThat(healthSetReal.getSpeed()).isEqualTo(SPEED);
    }
}
