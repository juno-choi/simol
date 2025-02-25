package com.simol.ouncommon.health.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.simol.ouncommon.health.entity.HealthEntity;

public interface HealthRepository extends JpaRepository<HealthEntity, Long> {
    
}
