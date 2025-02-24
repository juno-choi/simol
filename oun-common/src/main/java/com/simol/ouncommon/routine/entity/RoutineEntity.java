package com.simol.ouncommon.routine.entity;

import java.time.LocalDateTime;

import com.simol.ouncommon.routine.enums.RoutineStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
@Table(name = "routine")
public class RoutineEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "routine_id")
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(nullable = false)
    private String name;    //루틴 이름

    @Column(nullable = true)
    private String description; //루틴 설명

    @Enumerated(EnumType.STRING)
    private RoutineStatus status;   //루틴 상태

    @Column(nullable = false)
    private LocalDateTime createdAt; //루틴 생성일

    @Column(nullable = false)
    private LocalDateTime updatedAt; //루틴 수정일


    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
    
    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    @Builder
    private RoutineEntity(String name, String description, Long userId) {
        this.name = name;
        this.description = description;
        this.userId = userId;
        this.status = RoutineStatus.ACTIVE;
    }

    public static RoutineEntity create(String name, String description, Long userId) {
        return RoutineEntity.builder()
            .name(name)
            .description(description)
            .userId(userId)
            .build();
    }
}