package com.simol.simolcommon.routine.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.simol.simolcommon.routine.entity.RoutineEntity;

public interface RoutineRepository extends JpaRepository<RoutineEntity, Long>, CustomRoutineRepository {
    
}
