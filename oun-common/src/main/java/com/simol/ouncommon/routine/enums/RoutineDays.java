package com.simol.ouncommon.routine.enums;

public enum RoutineDays {
    MONDAY("월요일", 0),
    TUESDAY("화요일", 1),
    WEDNESDAY("수요일", 2),
    THURSDAY("목요일", 3),
    FRIDAY("금요일", 4),
    SATURDAY("토요일", 5),
    SUNDAY("일요일", 6);

    public final String value;
    public final int sort;

    RoutineDays(String value, int sort) {
        this.value = value;
        this.sort = sort;
    }
}
