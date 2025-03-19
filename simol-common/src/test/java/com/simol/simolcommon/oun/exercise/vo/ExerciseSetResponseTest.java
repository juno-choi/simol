package com.simol.simolcommon.oun.exercise.vo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.simol.simolcommon.oun.exercise.entity.ExerciseSetEntity;
import com.simol.simolcommon.oun.exercise.enums.ExerciseSetStatus;
import com.simol.simolcommon.oun.exercise.vo.ExerciseSetResponse;

public class ExerciseSetResponseTest {
    @Test
    @DisplayName("ExerciseSetResponse 생성 테스트")
    void ofSuccess() {
        final Long EXERCISE_SET_ID = 1L;
        final String DESCRIPTION = "test";
        final ExerciseSetStatus STATUS = ExerciseSetStatus.ACTIVE;

        ExerciseSetEntity exerciseSet = ExerciseSetEntity.builder()
            .id(EXERCISE_SET_ID)
            .description(DESCRIPTION)
            .status(STATUS)
            .number(1)
            .count(10)
            .weight(100)
            .distance(1000)
            .time(100)
            .speed(10)
            .build();

        ExerciseSetResponse exerciseSetResponse = ExerciseSetResponse.of(exerciseSet);

        Assertions.assertThat(exerciseSetResponse.getExerciseSetId()).isEqualTo(EXERCISE_SET_ID);
        Assertions.assertThat(exerciseSetResponse.getDescription()).isEqualTo(DESCRIPTION);
        Assertions.assertThat(exerciseSetResponse.getStatus()).isEqualTo(STATUS);
        Assertions.assertThat(exerciseSetResponse.getNumber()).isEqualTo(1);
        Assertions.assertThat(exerciseSetResponse.getCount()).isEqualTo(10);
        Assertions.assertThat(exerciseSetResponse.getWeight()).isEqualTo(100);
        Assertions.assertThat(exerciseSetResponse.getDistance()).isEqualTo(1000);
        Assertions.assertThat(exerciseSetResponse.getTime()).isEqualTo(100);
        Assertions.assertThat(exerciseSetResponse.getSpeed()).isEqualTo(10);
    }
}
