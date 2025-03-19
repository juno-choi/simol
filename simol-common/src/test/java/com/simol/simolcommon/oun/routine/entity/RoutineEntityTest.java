package com.simol.simolcommon.oun.routine.entity;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.simol.simolcommon.auth.entity.UserEntity;
import com.simol.simolcommon.oun.routine.dto.RoutineCreateRequest;
import com.simol.simolcommon.oun.routine.entity.RoutineEntity;
import com.simol.simolcommon.oun.routine.vo.RoutineResponse;

public class RoutineEntityTest {
    @Test
    @DisplayName("루틴 생성 테스트")
    void createSuccess() {
        UserEntity user = UserEntity.create("test@test.com", "test", "test", "test");
        RoutineCreateRequest request = RoutineCreateRequest.builder().name("test").description("test").build();
        RoutineEntity routineEntity = RoutineEntity.create(request, user);
        
        RoutineResponse response = RoutineResponse.of(routineEntity);

        Assertions.assertThat(response.getRoutineId()).isEqualTo(routineEntity.getId());
        Assertions.assertThat(response.getName()).isEqualTo(routineEntity.getName());
        Assertions.assertThat(response.getDescription()).isEqualTo(routineEntity.getDescription());
        Assertions.assertThat(response.getStatus()).isEqualTo(routineEntity.getStatus());
    }
}
