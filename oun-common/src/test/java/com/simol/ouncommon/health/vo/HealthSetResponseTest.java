package com.simol.ouncommon.health.vo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.simol.ouncommon.health.entity.HealthSetEntity;
import com.simol.ouncommon.health.enums.HealthSetStatus;

public class HealthSetResponseTest {
    @Test
    @DisplayName("HealthSetResponse 생성 테스트")
    void ofSuccess() {
        final Long HEALTH_SET_ID = 1L;
        final String DESCRIPTION = "test";
        final HealthSetStatus STATUS = HealthSetStatus.ACTIVE;
        final int SORT = 1;

        HealthSetEntity healthSet = HealthSetEntity.builder()
            .id(HEALTH_SET_ID)
            .description(DESCRIPTION)
            .status(STATUS)
            .sort(SORT)
            .build();

        HealthSetResponse healthSetResponse = HealthSetResponse.of(healthSet);

        Assertions.assertThat(healthSetResponse.getHealthSetId()).isEqualTo(HEALTH_SET_ID);
        Assertions.assertThat(healthSetResponse.getDescription()).isEqualTo(DESCRIPTION);
        Assertions.assertThat(healthSetResponse.getStatus()).isEqualTo(STATUS);
        Assertions.assertThat(healthSetResponse.getSort()).isEqualTo(SORT);
    }
}
