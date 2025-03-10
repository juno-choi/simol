package com.simol.ouncommon.health.entity;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.simol.ouncommon.auth.entity.UserEntity;
import com.simol.ouncommon.health.dto.HealthCreateRequest;
import com.simol.ouncommon.health.dto.HealthUpdateRequest;
import com.simol.ouncommon.health.enums.HealthStatus;
import com.simol.ouncommon.routine.dto.RoutineCreateRequest;
import com.simol.ouncommon.routine.entity.RoutineEntity;


public class HealthEntityTest {
    @Test
    @DisplayName("HealthEntity 생성 테스트")
    void createSuccess() {
        HealthCreateRequest healthCreateRequest = HealthCreateRequest.builder()
            .name("test")
            .description("test")
            .sort(1)
            .build();

        UserEntity user = UserEntity.create("test@test.com", "test", "test", "test");
        RoutineCreateRequest request = RoutineCreateRequest.builder().name("test").description("test").sort(1).build();
        RoutineEntity routine = RoutineEntity.create(request, user);
            
        HealthEntity health = HealthEntity.create(healthCreateRequest, routine, user);

        Assertions.assertThat(health.getName()).isEqualTo("test");
        Assertions.assertThat(health.getDescription()).isEqualTo("test");
        Assertions.assertThat(health.getSort()).isEqualTo(1);
        Assertions.assertThat(health.getStatus()).isEqualTo(HealthStatus.ACTIVE);
    }

    @Test
    @DisplayName("HealthEntity 수정 테스트")
    void updateSuccess() {
        HealthCreateRequest healthCreateRequest = HealthCreateRequest.builder()
            .name("test")
            .description("test")
            .sort(1)
            .build();

        UserEntity user = UserEntity.create("test@test.com", "test", "test", "test");
        RoutineCreateRequest request = RoutineCreateRequest.builder().name("test").description("test").sort(1).build();
        RoutineEntity routine = RoutineEntity.create(request, user); 
        
        HealthEntity health = HealthEntity.create(healthCreateRequest, routine, user);

        HealthUpdateRequest healthUpdateRequest = HealthUpdateRequest.builder()
            .name("test2")
            .description("test2")
            .sort(2)
            .status(HealthStatus.INACTIVE)
            .build();

        health.update(healthUpdateRequest);

        Assertions.assertThat(health.getName()).isEqualTo("test2");
        Assertions.assertThat(health.getDescription()).isEqualTo("test2");
        Assertions.assertThat(health.getSort()).isEqualTo(2);
        Assertions.assertThat(health.getStatus()).isEqualTo(HealthStatus.INACTIVE);
    }


}
