package com.simol.ouncommon.health.vo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.simol.ouncommon.health.entity.HealthSetEntity;
import com.simol.ouncommon.health.enums.HealthSetStatus;

public class HealthSetCreateResponseTest {
    @Test
    @DisplayName("HealthSetCreateResponse 생성 테스트")
    void ofSuccess() {
        final Long ID = 1L;
        final String DESCRIPTION = "test";
        final int SORT = 1;
        final HealthSetStatus STATUS = HealthSetStatus.ACTIVE;

        HealthSetEntity healthSet = HealthSetEntity.builder()
            .id(ID)
            .description(DESCRIPTION)
            .sort(SORT)
            .status(STATUS)
            .build();

        HealthSetCreateResponse healthSetCreateResponse = HealthSetCreateResponse.of(healthSet);

        Assertions.assertThat(healthSetCreateResponse.getId()).isEqualTo(ID);
        Assertions.assertThat(healthSetCreateResponse.getDescription()).isEqualTo(DESCRIPTION);
        Assertions.assertThat(healthSetCreateResponse.getSort()).isEqualTo(SORT);
        Assertions.assertThat(healthSetCreateResponse.getStatus()).isEqualTo(STATUS);
    }
}
