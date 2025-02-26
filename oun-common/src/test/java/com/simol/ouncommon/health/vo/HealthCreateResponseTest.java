package com.simol.ouncommon.health.vo;

import org.assertj.core.api.Assertions;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.simol.ouncommon.health.entity.HealthEntity;
import com.simol.ouncommon.health.enums.HealthStatus;

public class HealthCreateResponseTest {
    @Test
    @DisplayName("HealthCreateResponse 생성 테스트")
    void ofSuccess() {
        final Long HEALTH_ID = 1L;
        final String NAME = "test";
        final String DESCRIPTION = "test";
        final int SORT = 1;
        final HealthStatus STATUS = HealthStatus.ACTIVE;

        HealthEntity health = HealthEntity.builder()
            .id(HEALTH_ID)
            .name(NAME)
            .description(DESCRIPTION)
            .sort(SORT)
            .status(STATUS)
            .build();

        HealthCreateResponse healthCreateResponse = HealthCreateResponse.of(health);

        Assertions.assertThat(healthCreateResponse.getHealthId()).isEqualTo(HEALTH_ID);
        Assertions.assertThat(healthCreateResponse.getName()).isEqualTo(NAME);
        Assertions.assertThat(healthCreateResponse.getDescription()).isEqualTo(DESCRIPTION);
        Assertions.assertThat(healthCreateResponse.getSort()).isEqualTo(SORT);
        Assertions.assertThat(healthCreateResponse.getStatus()).isEqualTo(STATUS);
    }
}
