package com.simol.simolcommon.oun.routine.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.simol.simolcommon.oun.routine.entity.RoutineEntity;

public interface RoutineRepository extends JpaRepository<RoutineEntity, Long>, CustomRoutineRepository {
    
}
