package com.simol.simolcommon.oun.exercise.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ExerciseType {
    WEIGHT("웨이트"),
    CARDIO("유산소"),
    ;
    public final String description;
}
