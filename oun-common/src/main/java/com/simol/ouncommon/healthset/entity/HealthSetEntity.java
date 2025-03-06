package com.simol.ouncommon.healthset.entity;

import com.simol.ouncommon.global.entity.GlobalEntity;
import com.simol.ouncommon.health.dto.HealthHealthSetUpdateRequest;
import com.simol.ouncommon.health.entity.HealthEntity;
import com.simol.ouncommon.healthset.dto.HealthSetCreateRequest;
import com.simol.ouncommon.healthset.enums.HealthSetStatus;
import com.simol.ouncommon.healthset.enums.HealthSetType;

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
@Table(name = "HEALTH_SET")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class HealthSetEntity extends GlobalEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "health_set_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "health_id")
    private HealthEntity health;

    // 운동 타입
    @Enumerated(EnumType.STRING)
    private HealthSetType healthSetType;

    // 세트 번호
    private int setNumber;
    // 세트당 개수
    private int setCount;
    // 세트 무게
    private int setWeight;
    // 세트 거리
    private int setDistance;
    // 세트 시간
    private int setTime;
    // 세트 속도
    private int setSpeed;

    private String description;
    
    @Enumerated(EnumType.STRING)
    private HealthSetStatus status;

    public static HealthSetEntity create(HealthSetCreateRequest healthDetailCreateRequest, HealthEntity health) {
        return HealthSetEntity.builder()
            .health(health)
            .healthSetType(healthDetailCreateRequest.getHealthSetType())
            .setNumber(healthDetailCreateRequest.getSetNumber())
            .setCount(healthDetailCreateRequest.getSetCount())
            .setWeight(healthDetailCreateRequest.getSetWeight())
            .setDistance(healthDetailCreateRequest.getSetDistance())
            .setTime(healthDetailCreateRequest.getSetTime())
            .setSpeed(healthDetailCreateRequest.getSetSpeed())
            .description(healthDetailCreateRequest.getDescription())
            .status(HealthSetStatus.ACTIVE)
            .build();
    }

    public void updateHealth(HealthEntity healthEntity) {
        this.health = healthEntity;
    }
    
    public void update(HealthHealthSetUpdateRequest healthSetRequest) {
        this.healthSetType = healthSetRequest.getHealthSetType();
        this.setNumber = healthSetRequest.getSetNumber();
        this.setCount = healthSetRequest.getSetCount();
        this.setWeight = healthSetRequest.getSetWeight();
        this.setDistance = healthSetRequest.getSetDistance();
        this.setTime = healthSetRequest.getSetTime();
        this.setSpeed = healthSetRequest.getSetSpeed();
        this.description = healthSetRequest.getDescription();
    }

    public static HealthSetEntity create(HealthHealthSetUpdateRequest healthSetRequest, HealthEntity health) {
        return HealthSetEntity.builder()
            .health(health)
            .healthSetType(healthSetRequest.getHealthSetType())
            .setNumber(healthSetRequest.getSetNumber())
            .setCount(healthSetRequest.getSetCount())
            .setWeight(healthSetRequest.getSetWeight())
            .setDistance(healthSetRequest.getSetDistance())
            .setTime(healthSetRequest.getSetTime())
            .setSpeed(healthSetRequest.getSetSpeed())
            .description(healthSetRequest.getDescription())
            .status(HealthSetStatus.ACTIVE)
            .build();
    }
}