package com.simol.simolcommon.routine.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.simol.simolcommon.routine.entity.RoutineEntity;

public interface CustomRoutineRepository {
    Page<RoutineEntity> findAllByPage(Pageable pageable, Long userId);
}
