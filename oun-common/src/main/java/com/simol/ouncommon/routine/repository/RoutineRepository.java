package com.simol.ouncommon.routine.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.simol.ouncommon.routine.entity.RoutineEntity;

public interface RoutineRepository extends JpaRepository<RoutineEntity, Long>, CustomRoutineRepository {
    
}
