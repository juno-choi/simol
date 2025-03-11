package com.simol.ouncommon.health.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum HealthType {
    WEIGHT("웨이트"),
    CARDIO("유산소"),
    ;
    public final String description;
}
