package com.simol.simolcommon.routine.vo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.simol.simolcommon.routine.entity.RoutineEntity;
import com.simol.simolcommon.routine.enums.RoutineStatus;

public class RoutineCreateResponseTest {
    @Test
    @DisplayName("RoutineCreateResponse 생성 테스트")
    void ofSuccess() {
        final String NAME = "test";
        final String DESCRIPTION = "test";
        final RoutineStatus STATUS = RoutineStatus.ACTIVE;

        RoutineEntity routineEntity = RoutineEntity.builder()
            .name(NAME)
            .description(DESCRIPTION)
            .status(STATUS)
            .build();

        RoutineCreateResponse routineCreateResponse = RoutineCreateResponse.of(routineEntity);

        Assertions.assertThat(routineCreateResponse.getName()).isEqualTo(NAME);
        Assertions.assertThat(routineCreateResponse.getDescription()).isEqualTo(DESCRIPTION);
        Assertions.assertThat(routineCreateResponse.getStatus()).isEqualTo(STATUS);
    }
}
