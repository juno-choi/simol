package com.simol.simolcommon.kong.diary.entity;

import com.simol.simolcommon.kong.diary.enums.DiaryEmotion;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Embeddable
public class DiaryUserContent {
    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "user_emotion")
    @Enumerated(EnumType.STRING)
    private DiaryEmotion userEmotion;
}
