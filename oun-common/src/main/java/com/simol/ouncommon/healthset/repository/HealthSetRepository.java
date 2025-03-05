package com.simol.ouncommon.healthset.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.simol.ouncommon.healthset.entity.HealthSetEntity;

public interface HealthSetRepository extends JpaRepository<HealthSetEntity, Long> {
    
}
