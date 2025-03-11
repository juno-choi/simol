package com.simol.ouncommon.healthset.vo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.simol.ouncommon.healthset.entity.HealthSetEntity;
import com.simol.ouncommon.healthset.enums.HealthSetStatus;

public class HealthSetResponseTest {
    @Test
    @DisplayName("HealthSetResponse 생성 테스트")
    void ofSuccess() {
        final Long HEALTH_SET_ID = 1L;
        final String DESCRIPTION = "test";
        final HealthSetStatus STATUS = HealthSetStatus.ACTIVE;

        HealthSetEntity healthSet = HealthSetEntity.builder()
            .id(HEALTH_SET_ID)
            .description(DESCRIPTION)
            .status(STATUS)
            .setNumber(1)
            .setCount(10)
            .setWeight(100)
            .setDistance(1000)
            .setTime(100)
            .setSpeed(10)
            .build();

        HealthSetResponse healthSetResponse = HealthSetResponse.of(healthSet);

        Assertions.assertThat(healthSetResponse.getHealthSetId()).isEqualTo(HEALTH_SET_ID);
        Assertions.assertThat(healthSetResponse.getDescription()).isEqualTo(DESCRIPTION);
        Assertions.assertThat(healthSetResponse.getStatus()).isEqualTo(STATUS);
        Assertions.assertThat(healthSetResponse.getSetNumber()).isEqualTo(1);
        Assertions.assertThat(healthSetResponse.getSetCount()).isEqualTo(10);
        Assertions.assertThat(healthSetResponse.getSetWeight()).isEqualTo(100);
        Assertions.assertThat(healthSetResponse.getSetDistance()).isEqualTo(1000);
        Assertions.assertThat(healthSetResponse.getSetTime()).isEqualTo(100);
        Assertions.assertThat(healthSetResponse.getSetSpeed()).isEqualTo(10);
    }
}
