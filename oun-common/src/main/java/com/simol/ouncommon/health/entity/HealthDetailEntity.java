package com.simol.ouncommon.health.entity;

import com.simol.ouncommon.auth.entity.UserEntity;
import com.simol.ouncommon.global.entity.GlobalEntity;
import com.simol.ouncommon.health.dto.HealthDetailCreateRequest;
import com.simol.ouncommon.health.enums.HealthDetailStatus;

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
@Table(name = "HEALTH_DETAIL")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class HealthDetailEntity extends GlobalEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "health_detail_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "health_id")
    private HealthEntity health;

    private String description;
    
    private int sets;   //세트 set이 예약어라 sets로 변경

    private int count;
    
    private int weight;

    private int distance;

    private int speed;

    private int time;

    @Enumerated(EnumType.STRING)
    private HealthDetailStatus status;

    public static HealthDetailEntity create(HealthDetailCreateRequest healthDetailCreateRequest, HealthEntity health, UserEntity user) {
        return HealthDetailEntity.builder()
            .health(health)
            .description(healthDetailCreateRequest.getDescription())
            .sets(healthDetailCreateRequest.getSets())
            .count(healthDetailCreateRequest.getCount())
            .weight(healthDetailCreateRequest.getWeight())
            .distance(healthDetailCreateRequest.getDistance())
            .speed(healthDetailCreateRequest.getSpeed())
            .time(healthDetailCreateRequest.getTime())
            .status(HealthDetailStatus.ACTIVE)
            .build();
    }
}