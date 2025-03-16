package com.simol.ouncommon.exercise.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ExerciseStatus {
    ACTIVE("활성"),
    INACTIVE("비활성")
    ;
    public String description;
}
