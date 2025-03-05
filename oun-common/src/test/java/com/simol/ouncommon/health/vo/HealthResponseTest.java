package com.simol.ouncommon.health.vo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.simol.ouncommon.health.entity.HealthEntity;
import com.simol.ouncommon.health.enums.HealthStatus;

public class HealthResponseTest {
    @Test
    @DisplayName("HealthResponse 생성 테스트")
    void ofSuccess() {
        final String NAME = "test";
        final String DESCRIPTION = "test";
        final int SORT = 1;
        final HealthStatus STATUS = HealthStatus.ACTIVE;

        HealthEntity health = HealthEntity.builder()
            .name(NAME)
            .description(DESCRIPTION)
            .sort(SORT)
            .status(STATUS)
            .build();

        HealthResponse healthResponse = HealthResponse.of(health);

        Assertions.assertThat(healthResponse.getName()).isEqualTo(NAME);
        Assertions.assertThat(healthResponse.getDescription()).isEqualTo(DESCRIPTION);
        Assertions.assertThat(healthResponse.getSort()).isEqualTo(SORT);
        Assertions.assertThat(healthResponse.getStatus()).isEqualTo(STATUS);
    }
}
