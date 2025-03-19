package com.simol.simolcommon.oun.exercise.entity;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.simol.simolcommon.auth.entity.UserEntity;
import com.simol.simolcommon.oun.exercise.dto.ExerciseCreateRequest;
import com.simol.simolcommon.oun.exercise.dto.ExerciseUpdateRequest;
import com.simol.simolcommon.oun.exercise.entity.ExerciseEntity;
import com.simol.simolcommon.oun.exercise.enums.ExerciseStatus;
import com.simol.simolcommon.oun.routine.dto.RoutineCreateRequest;
import com.simol.simolcommon.oun.routine.entity.RoutineEntity;

public class ExerciseEntityTest {
    @Test
    @DisplayName("HealthEntity 생성 테스트")
    void createSuccess() {
        ExerciseCreateRequest exerciseCreateRequest = ExerciseCreateRequest.builder()
            .name("test")
            .description("test")
            .sort(1)
            .build();

        UserEntity user = UserEntity.create("test@test.com", "test", "test", "test");
        RoutineCreateRequest request = RoutineCreateRequest.builder().name("test").description("test").build();
        RoutineEntity routine = RoutineEntity.create(request, user);
            
        ExerciseEntity exercise = ExerciseEntity.create(exerciseCreateRequest, routine, user);

        Assertions.assertThat(exercise.getName()).isEqualTo("test");
        Assertions.assertThat(exercise.getDescription()).isEqualTo("test");
        Assertions.assertThat(exercise.getStatus()).isEqualTo(ExerciseStatus.ACTIVE);
    }

    @Test
    @DisplayName("HealthEntity 수정 테스트")
    void updateSuccess() {
        ExerciseCreateRequest exerciseCreateRequest = ExerciseCreateRequest.builder()
            .name("test")
            .description("test")
            .sort(1)
            .build();

        UserEntity user = UserEntity.create("test@test.com", "test", "test", "test");
        RoutineCreateRequest request = RoutineCreateRequest.builder().name("test").description("test").build();
        RoutineEntity routine = RoutineEntity.create(request, user); 
        
        ExerciseEntity exercise = ExerciseEntity.create(exerciseCreateRequest, routine, user);

        ExerciseUpdateRequest exerciseUpdateRequest = ExerciseUpdateRequest.builder()
            .name("test2")
            .description("test2")
            .sort(2)
            .status(ExerciseStatus.INACTIVE)
            .build();

        exercise.update(exerciseUpdateRequest);

        Assertions.assertThat(exercise.getName()).isEqualTo("test2");
        Assertions.assertThat(exercise.getDescription()).isEqualTo("test2");
        Assertions.assertThat(exercise.getSort()).isEqualTo(2);
        Assertions.assertThat(exercise.getStatus()).isEqualTo(ExerciseStatus.INACTIVE);
    }


}
