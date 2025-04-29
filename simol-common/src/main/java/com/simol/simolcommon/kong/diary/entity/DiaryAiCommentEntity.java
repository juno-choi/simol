package com.simol.simolcommon.kong.diary.entity;

import com.simol.simolcommon.global.entity.GlobalEntity;
import com.simol.simolcommon.kong.diary.enums.DiaryEmotion;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "diary_ai_comment")
public class DiaryAiCommentEntity extends GlobalEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "diary_ai_comment_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "diary_id")
    private DiaryEntity diaryId;

    @Column(name = "comment")
    private String comment;

    @Column(name = "emotion")
    private DiaryEmotion emotion;
}
