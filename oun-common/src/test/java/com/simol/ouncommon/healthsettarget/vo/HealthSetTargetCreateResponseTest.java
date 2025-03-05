package com.simol.ouncommon.healthsettarget.vo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.simol.ouncommon.healthset.entity.HealthSetEntity;
import com.simol.ouncommon.healthsettarget.entity.HealthSetTargetEntity;

public class HealthSetTargetCreateResponseTest {
    @Test
    @DisplayName("HealthSetTargetCreateResponse 생성 테스트")
    void ofSuccess() {
        final Long HEALTH_SET_TARGET_ID = 1L;
        final Long HEALTH_SET_ID = 1L;
        final int NUMBER = 1;
        final double WEIGHT = 1.0;
        final double DISTANCE = 1.0;
        final int TIME = 1;
        final double SPEED = 1.0;

        HealthSetEntity healthSet = HealthSetEntity.builder()
            .id(HEALTH_SET_ID)
            .build();

        HealthSetTargetEntity healthSetTarget = HealthSetTargetEntity.builder()
            .id(HEALTH_SET_TARGET_ID)
            .healthSet(healthSet)
            .number(NUMBER)
            .weight(WEIGHT)
            .distance(DISTANCE)
            .time(TIME)
            .speed(SPEED)
            .build();

        HealthSetTargetCreateResponse healthSetTargetCreateResponse = HealthSetTargetCreateResponse.of(healthSetTarget);

        Assertions.assertThat(healthSetTargetCreateResponse.getHealthSetTargetId()).isEqualTo(HEALTH_SET_TARGET_ID);
        Assertions.assertThat(healthSetTargetCreateResponse.getHealthSetId()).isEqualTo(HEALTH_SET_ID);
        Assertions.assertThat(healthSetTargetCreateResponse.getNumber()).isEqualTo(NUMBER);
        Assertions.assertThat(healthSetTargetCreateResponse.getWeight()).isEqualTo(WEIGHT);
        Assertions.assertThat(healthSetTargetCreateResponse.getDistance()).isEqualTo(DISTANCE);
        Assertions.assertThat(healthSetTargetCreateResponse.getTime()).isEqualTo(TIME);
        Assertions.assertThat(healthSetTargetCreateResponse.getSpeed()).isEqualTo(SPEED);
    }
}
