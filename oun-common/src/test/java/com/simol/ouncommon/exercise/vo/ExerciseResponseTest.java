package com.simol.ouncommon.exercise.vo;

import java.util.ArrayList;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.simol.ouncommon.exercise.entity.ExerciseEntity;
import com.simol.ouncommon.exercise.enums.ExerciseStatus;

public class ExerciseResponseTest {
    @Test
    @DisplayName("ExerciseResponse 생성 테스트")
    void ofSuccess() {
        final String NAME = "test";
        final String DESCRIPTION = "test";
        final int SORT = 1;
        final ExerciseStatus STATUS = ExerciseStatus.ACTIVE;

        ExerciseEntity exercise = ExerciseEntity.builder()
            .name(NAME)
            .description(DESCRIPTION)
            .sort(SORT)
            .status(STATUS)
            .exerciseSetList(new ArrayList<>())
            .build();

        ExerciseResponse exerciseResponse = ExerciseResponse.of(exercise);

        Assertions.assertThat(exerciseResponse.getName()).isEqualTo(NAME);
        Assertions.assertThat(exerciseResponse.getDescription()).isEqualTo(DESCRIPTION);
        Assertions.assertThat(exerciseResponse.getSort()).isEqualTo(SORT);
        Assertions.assertThat(exerciseResponse.getStatus()).isEqualTo(STATUS);
    }
}
