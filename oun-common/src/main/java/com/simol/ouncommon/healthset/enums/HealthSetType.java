package com.simol.ouncommon.healthset.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum HealthSetType {
    WEIGHT("무게"),
    RUNNING("달리기"),
    ;
    public String description;
}
