package com.simol.ouncommon.routine.vo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.simol.ouncommon.routine.entity.RoutineEntity;
import com.simol.ouncommon.routine.enums.RoutineStatus;

public class RoutineCreateResponseTest {
    @Test
    @DisplayName("RoutineCreateResponse 생성 테스트")
    void ofSuccess() {
        final Long ROUTINE_ID = 1L;
        final String NAME = "test";
        final String DESCRIPTION = "test";
        final RoutineStatus STATUS = RoutineStatus.ACTIVE;

        RoutineEntity routineEntity = RoutineEntity.builder()
            .id(ROUTINE_ID)
            .name(NAME)
            .description(DESCRIPTION)
            .status(STATUS)
            .build();

        RoutineCreateResponse routineCreateResponse = RoutineCreateResponse.of(routineEntity);

        Assertions.assertThat(routineCreateResponse.getRoutineId()).isEqualTo(ROUTINE_ID);
        Assertions.assertThat(routineCreateResponse.getName()).isEqualTo(NAME);
        Assertions.assertThat(routineCreateResponse.getDescription()).isEqualTo(DESCRIPTION);
        Assertions.assertThat(routineCreateResponse.getStatus()).isEqualTo(STATUS);
    }
}
