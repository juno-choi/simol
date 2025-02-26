package com.simol.ouncommon.health.entity;

import com.simol.ouncommon.global.entity.GlobalEntity;
import com.simol.ouncommon.health.dto.HealthSetTargetCreateRequest;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "HEALTH_SET_TARGET")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class HealthSetTargetEntity extends GlobalEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "health_set_target_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "health_set_id")
    private HealthSetEntity healthSet;

    private int number;

    private double weight;

    private double distance;

    private int time;

    private double speed;

    public static HealthSetTargetEntity create(HealthSetTargetCreateRequest healthSetTargetCreateRequest, HealthSetEntity healthSet) {
        return HealthSetTargetEntity.builder()
            .healthSet(healthSet)
            .number(healthSetTargetCreateRequest.getNumber())
            .weight(healthSetTargetCreateRequest.getWeight())
            .distance(healthSetTargetCreateRequest.getDistance())
            .time(healthSetTargetCreateRequest.getTime())
            .speed(healthSetTargetCreateRequest.getSpeed())
            .build();
    }
}