package com.simol.ouncommon.healthset.vo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.simol.ouncommon.healthset.entity.HealthSetEntity;
import com.simol.ouncommon.healthset.enums.HealthSetStatus;

public class HealthSetCreateResponseTest {
    @Test
    @DisplayName("HealthSetCreateResponse 생성 테스트")
    void ofSuccess() {
        final Long ID = 1L;
        final String DESCRIPTION = "test";
        final HealthSetStatus STATUS = HealthSetStatus.ACTIVE;

        HealthSetEntity healthSet = HealthSetEntity.builder()
            .id(ID)
            .description(DESCRIPTION)
            .setNumber(1)
            .setCount(10)
            .setWeight(100)
            .setDistance(1000)
            .setTime(100)
            .setSpeed(10)
            .status(STATUS)
            .build();

        HealthSetCreateResponse healthSetCreateResponse = HealthSetCreateResponse.of(healthSet);

        Assertions.assertThat(healthSetCreateResponse.getId()).isEqualTo(ID);
        Assertions.assertThat(healthSetCreateResponse.getDescription()).isEqualTo(DESCRIPTION);
        Assertions.assertThat(healthSetCreateResponse.getStatus()).isEqualTo(STATUS);
        Assertions.assertThat(healthSetCreateResponse.getSetNumber()).isEqualTo(1);
        Assertions.assertThat(healthSetCreateResponse.getSetCount()).isEqualTo(10);
        Assertions.assertThat(healthSetCreateResponse.getSetWeight()).isEqualTo(100);
        Assertions.assertThat(healthSetCreateResponse.getSetDistance()).isEqualTo(1000);
        Assertions.assertThat(healthSetCreateResponse.getSetTime()).isEqualTo(100);
        Assertions.assertThat(healthSetCreateResponse.getSetSpeed()).isEqualTo(10);
    }
}
