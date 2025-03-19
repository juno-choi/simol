package com.simol.simolcommon.routine.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum RoutineStatus {
    ACTIVE("활성화"),
    INACTIVE("비활성화"),
    ;

    public String description;
}
