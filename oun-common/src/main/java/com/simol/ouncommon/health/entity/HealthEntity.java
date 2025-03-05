package com.simol.ouncommon.health.entity;

import com.simol.ouncommon.auth.entity.UserEntity;
import com.simol.ouncommon.global.entity.GlobalEntity;
import com.simol.ouncommon.health.dto.HealthCreateRequest;
import com.simol.ouncommon.health.dto.HealthUpdateRequest;
import com.simol.ouncommon.health.enums.HealthStatus;
import com.simol.ouncommon.routine.entity.RoutineEntity;

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
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "HEALTH")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class HealthEntity extends GlobalEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "health_id")
    private Long id;

    // rotine 과 연관관계 매핑
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "routine_id")
    private RoutineEntity routine;

    // health 상태
    @Enumerated(EnumType.STRING)
    private HealthStatus status;

    // health 이름
    private String name;

    // health 설명
    private String description;

    // health 순서
    private int sort;

    public static HealthEntity create(HealthCreateRequest healthCreateRequest, RoutineEntity routine, UserEntity user) {
        return HealthEntity.builder()
                .routine(routine)
                .name(healthCreateRequest.getName())
                .description(healthCreateRequest.getDescription())
                .sort(healthCreateRequest.getSort())
                .status(HealthStatus.ACTIVE)
                .build();
    }

    public void update(HealthUpdateRequest healthUpdateRequest) {
        this.name = healthUpdateRequest.getName();
        this.description = healthUpdateRequest.getDescription();
        this.sort = healthUpdateRequest.getSort();
        this.status = healthUpdateRequest.getStatus();
    }

}
