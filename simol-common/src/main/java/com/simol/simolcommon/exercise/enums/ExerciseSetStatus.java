package com.simol.simolcommon.exercise.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ExerciseSetStatus {
    ACTIVE("활성"),
    INACTIVE("비활성")
    ;
    public String description;
}
