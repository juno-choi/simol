package com.simol.ouncommon.routine.entity;

import java.util.ArrayList;
import java.util.List;

import com.simol.ouncommon.auth.entity.UserEntity;
import com.simol.ouncommon.global.entity.GlobalEntity;
import com.simol.ouncommon.health.entity.HealthEntity;
import com.simol.ouncommon.routine.dto.RoutineUpdateRequest;
import com.simol.ouncommon.routine.enums.RoutineStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "routine")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RoutineEntity extends GlobalEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "routine_id")
    private Long id;

    // user와 연관관계 맺기
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @OneToMany(mappedBy = "routine", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HealthEntity> healthList = new ArrayList<>();

    @Column(nullable = false)
    private String name;    //루틴 이름

    @Column(nullable = true)
    private String description; //루틴 설명

    @Enumerated(EnumType.STRING)
    private RoutineStatus status;   //루틴 상태

    @Builder
    protected RoutineEntity(String name, String description, RoutineStatus status, List<HealthEntity> healthList, UserEntity user) {
        this.name = name;
        this.description = description;
        this.status = status;
        this.user = user;
        this.healthList = healthList;
    }

    public static RoutineEntity create(String name, String description, UserEntity user) {
        return RoutineEntity.builder()
            .name(name)
            .description(description)
            .status(RoutineStatus.ACTIVE)
            .user(user)
            .healthList(new ArrayList<>())
            .build();
    }

    public void update(RoutineUpdateRequest routineUpdateRequest) {
        this.name = routineUpdateRequest.getName();
        this.description = routineUpdateRequest.getDescription();
        this.status = routineUpdateRequest.getStatus();
    }

    public void addHealth(HealthEntity health) {
        healthList.add(health);
        health.updateRoutine(this);
    }
}   