package com.simol.ouncommon.routine.entity;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.simol.ouncommon.auth.entity.UserEntity;
import com.simol.ouncommon.routine.dto.RoutineCreateRequest;
import com.simol.ouncommon.routine.vo.RoutineResponse;

public class RoutineEntityTest {
    @Test
    @DisplayName("루틴 생성 테스트")
    void createSuccess() {
        UserEntity user = UserEntity.create("test@test.com", "test", "test", "test");
        RoutineCreateRequest request = RoutineCreateRequest.builder().name("test").description("test").sort(1).build();
        RoutineEntity routineEntity = RoutineEntity.create(request, user);
        
        RoutineResponse response = RoutineResponse.of(routineEntity);

        Assertions.assertThat(response.getRoutineId()).isEqualTo(routineEntity.getId());
        Assertions.assertThat(response.getName()).isEqualTo(routineEntity.getName());
        Assertions.assertThat(response.getDescription()).isEqualTo(routineEntity.getDescription());
        Assertions.assertThat(response.getStatus()).isEqualTo(routineEntity.getStatus());
    }
}
