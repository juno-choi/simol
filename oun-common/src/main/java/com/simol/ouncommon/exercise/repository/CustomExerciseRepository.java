package com.simol.ouncommon.exercise.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.simol.ouncommon.exercise.entity.ExerciseEntity;

public interface CustomExerciseRepository {
    Page<ExerciseEntity> findAllByPage(Pageable pageable, Long routineId);
}
