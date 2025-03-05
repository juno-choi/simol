package com.simol.ouncommon.healthset.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum HealthSetStatus {
    ACTIVE("활성"),
    INACTIVE("비활성")
    ;
    public String description;
}
