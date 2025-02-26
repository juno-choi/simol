package com.simol.ouncommon.health.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.simol.ouncommon.health.entity.HealthSetTargetEntity;

public interface HealthSetTargetRepository extends JpaRepository<HealthSetTargetEntity, Long> {
    
}
