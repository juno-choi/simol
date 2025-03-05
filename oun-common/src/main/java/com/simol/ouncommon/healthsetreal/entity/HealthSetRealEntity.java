package com.simol.ouncommon.healthsetreal.entity;

import com.simol.ouncommon.global.entity.GlobalEntity;
import com.simol.ouncommon.healthset.entity.HealthSetEntity;
import com.simol.ouncommon.healthsetreal.dto.HealthSetRealCreateRequest;

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
@Table(name = "HEALTH_SET_REAL")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
public class HealthSetRealEntity extends GlobalEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "health_set_real_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "health_set_id")
    private HealthSetEntity healthSet;

    private int number;

    private double weight;

    private double distance;

    private int time;

    private double speed;

    public static HealthSetRealEntity create(HealthSetRealCreateRequest healthSetRealCreateRequest, HealthSetEntity healthSet) {
        return HealthSetRealEntity.builder()
            .healthSet(healthSet)
            .number(healthSetRealCreateRequest.getNumber())
            .weight(healthSetRealCreateRequest.getWeight())
            .distance(healthSetRealCreateRequest.getDistance())
            .time(healthSetRealCreateRequest.getTime())
            .speed(healthSetRealCreateRequest.getSpeed())
            .build();
    }
    
}

