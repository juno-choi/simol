package com.simol.simolcommon.oun.exercise.vo;

import org.assertj.core.api.Assertions;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.simol.simolcommon.oun.exercise.entity.ExerciseEntity;
import com.simol.simolcommon.oun.exercise.enums.ExerciseStatus;
import com.simol.simolcommon.oun.exercise.vo.ExerciseCreateResponse;

public class ExerciseCreateResponseTest {
    @Test
    @DisplayName("ExerciseCreateResponse 생성 테스트")
    void ofSuccess() {
        final Long EXERCISE_ID = 1L;
        final String NAME = "test";
        final String DESCRIPTION = "test";
        final int SORT = 1;
        final ExerciseStatus STATUS = ExerciseStatus.ACTIVE;

        ExerciseEntity exercise = ExerciseEntity.builder()
            .id(EXERCISE_ID)
            .name(NAME)
            .description(DESCRIPTION)
            .sort(SORT)
            .status(STATUS)
            .build();

        ExerciseCreateResponse exerciseCreateResponse = ExerciseCreateResponse.of(exercise);

        Assertions.assertThat(exerciseCreateResponse.getExerciseId()).isEqualTo(EXERCISE_ID);
        Assertions.assertThat(exerciseCreateResponse.getName()).isEqualTo(NAME);
        Assertions.assertThat(exerciseCreateResponse.getDescription()).isEqualTo(DESCRIPTION);
        Assertions.assertThat(exerciseCreateResponse.getSort()).isEqualTo(SORT);
        Assertions.assertThat(exerciseCreateResponse.getStatus()).isEqualTo(STATUS);
    }
}
