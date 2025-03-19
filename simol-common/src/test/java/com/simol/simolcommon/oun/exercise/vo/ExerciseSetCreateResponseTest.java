package com.simol.simolcommon.oun.exercise.vo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.simol.simolcommon.oun.exercise.entity.ExerciseSetEntity;
import com.simol.simolcommon.oun.exercise.enums.ExerciseSetStatus;
import com.simol.simolcommon.oun.exercise.vo.ExerciseSetCreateResponse;

public class ExerciseSetCreateResponseTest {
    @Test
    @DisplayName("ExerciseSetCreateResponse 생성 테스트")
    void ofSuccess() {
        final Long ID = 1L;
        final String DESCRIPTION = "test";
        final ExerciseSetStatus STATUS = ExerciseSetStatus.ACTIVE;

        ExerciseSetEntity exerciseSet = ExerciseSetEntity.builder()
            .id(ID)
            .description(DESCRIPTION)
            .number(1)
            .count(10)
            .weight(100)
            .distance(1000)
            .time(100)
            .speed(10)
            .status(STATUS)
            .build();

        ExerciseSetCreateResponse exerciseSetCreateResponse = ExerciseSetCreateResponse.of(exerciseSet);

        Assertions.assertThat(exerciseSetCreateResponse.getId()).isEqualTo(ID);
        Assertions.assertThat(exerciseSetCreateResponse.getDescription()).isEqualTo(DESCRIPTION);
        Assertions.assertThat(exerciseSetCreateResponse.getStatus()).isEqualTo(STATUS);
        Assertions.assertThat(exerciseSetCreateResponse.getNumber()).isEqualTo(1);
        Assertions.assertThat(exerciseSetCreateResponse.getCount()).isEqualTo(10);
        Assertions.assertThat(exerciseSetCreateResponse.getWeight()).isEqualTo(100);
        Assertions.assertThat(exerciseSetCreateResponse.getDistance()).isEqualTo(1000);
        Assertions.assertThat(exerciseSetCreateResponse.getTime()).isEqualTo(100);
        Assertions.assertThat(exerciseSetCreateResponse.getSpeed()).isEqualTo(10);
    }
}
