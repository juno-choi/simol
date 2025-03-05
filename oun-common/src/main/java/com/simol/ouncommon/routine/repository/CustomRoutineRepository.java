package com.simol.ouncommon.routine.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.simol.ouncommon.routine.entity.RoutineEntity;

public interface CustomRoutineRepository {
    Page<RoutineEntity> findAllByPage(Pageable pageable, Long userId);
}
