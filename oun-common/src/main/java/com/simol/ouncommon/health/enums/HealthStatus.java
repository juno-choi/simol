package com.simol.ouncommon.health.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum HealthStatus {
    ACTIVE("활성"),
    INACTIVE("비활성")
    ;
    private String description;
}
