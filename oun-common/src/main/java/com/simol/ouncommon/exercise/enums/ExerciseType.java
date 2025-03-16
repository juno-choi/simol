package com.simol.ouncommon.exercise.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ExerciseType {
    WEIGHT("웨이트"),
    CARDIO("유산소"),
    ;
    public final String description;
}
