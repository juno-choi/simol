package com.simol.ouncommon.healthsettarget.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.simol.ouncommon.healthsettarget.entity.HealthSetTargetEntity;

public interface HealthSetTargetRepository extends JpaRepository<HealthSetTargetEntity, Long> {
    
}
