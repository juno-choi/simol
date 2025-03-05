package com.simol.ouncommon.routine.entity;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.simol.ouncommon.auth.entity.UserEntity;
import com.simol.ouncommon.routine.dto.RoutineUpdateRequest;
import com.simol.ouncommon.routine.enums.RoutineStatus;
import com.simol.ouncommon.routine.vo.RoutineResponse;

public class RoutineEntityTest {
    @Test
    @DisplayName("루틴 생성 테스트")
    void createSuccess() {
        UserEntity user = UserEntity.create("test@test.com", "test", "test", "test");
        RoutineEntity routineEntity = RoutineEntity.create("test", "test", user);
        
        RoutineResponse response = RoutineResponse.of(routineEntity);

        Assertions.assertThat(response.getRoutineId()).isEqualTo(routineEntity.getId());
        Assertions.assertThat(response.getName()).isEqualTo(routineEntity.getName());
        Assertions.assertThat(response.getDescription()).isEqualTo(routineEntity.getDescription());
        Assertions.assertThat(response.getStatus()).isEqualTo(routineEntity.getStatus());
    }

    @Test
    @DisplayName("루틴 수정 테스트")
    void updateSuccess() {
        UserEntity user = UserEntity.create("test@test.com", "test", "test", "test");
        RoutineEntity routineEntity = RoutineEntity.create("test", "test", user);

        RoutineUpdateRequest routineUpdateRequest = RoutineUpdateRequest.builder()
            .routineId(1L)
            .name("test2")
            .description("test2")
            .status(RoutineStatus.ACTIVE)
            .build();

        routineEntity.update(routineUpdateRequest);

        RoutineResponse response = RoutineResponse.of(routineEntity);

        Assertions.assertThat(response.getName()).isEqualTo(routineUpdateRequest.getName());
        Assertions.assertThat(response.getDescription()).isEqualTo(routineUpdateRequest.getDescription());
        Assertions.assertThat(response.getStatus()).isEqualTo(routineUpdateRequest.getStatus());
    }
}
