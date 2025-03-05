package com.simol.ouncommon.healthset.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.simol.ouncommon.healthsetreal.entity.HealthSetRealEntity;

public interface HealthSetRealRepository extends JpaRepository<HealthSetRealEntity, Long> {
    
}
