package com.simol.simolcommon.exercise.entity;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.simol.simolcommon.auth.entity.UserEntity;
import com.simol.simolcommon.exercise.dto.ExerciseSetCreateRequest;
import com.simol.simolcommon.exercise.enums.ExerciseStatus;
import com.simol.simolcommon.routine.dto.RoutineCreateRequest;
import com.simol.simolcommon.routine.entity.RoutineEntity;

public class ExerciseSetEntityTest {
    @Test
    @DisplayName("HealthSetEntity 생성 테스트")
    void createSuccess() {
        ExerciseSetCreateRequest exerciseSetCreateRequest = ExerciseSetCreateRequest.builder()
            .description("test")
            .number(1)
            .count(10)
            .weight(100)
            .distance(1000)
            .time(100)
            .speed(10)
            .build();

        UserEntity user = UserEntity.create("test@test.com", "test", "test", "test");
        RoutineCreateRequest request = RoutineCreateRequest.builder().name("test").description("test").build();
        RoutineEntity routine = RoutineEntity.create(request, user);
        ExerciseEntity exercise = ExerciseEntity.builder()
            .id(1L)
            .routine(routine)
            .name("test")
            .description("test")
            .sort(1)
            .status(ExerciseStatus.ACTIVE)
            .build();
        
        ExerciseSetEntity exerciseSet = ExerciseSetEntity.create(exerciseSetCreateRequest, exercise);

        Assertions.assertThat(exerciseSet.getExercise()).isEqualTo(exercise);
        Assertions.assertThat(exerciseSet.getDescription()).isEqualTo("test");
        Assertions.assertThat(exerciseSet.getNumber()).isEqualTo(1);
        Assertions.assertThat(exerciseSet.getCount()).isEqualTo(10);
        Assertions.assertThat(exerciseSet.getWeight()).isEqualTo(100);
        Assertions.assertThat(exerciseSet.getDistance()).isEqualTo(1000);
        Assertions.assertThat(exerciseSet.getTime()).isEqualTo(100);
    }
}
