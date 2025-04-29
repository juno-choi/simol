package com.simol.simolcommon.kong.diary.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum DiaryEmotion {
    HAPPY("행복"),
    SAD("슬픔"),
    ANGRY("분노"),
    EXCITED("신남"),
    SLEEPY("졸림"),
    NERVOUS("긴장"),
    CONFUSED("혼란"),
    ;
    private String description;
}
