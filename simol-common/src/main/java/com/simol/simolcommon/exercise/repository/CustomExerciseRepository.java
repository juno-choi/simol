package com.simol.simolcommon.exercise.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.simol.simolcommon.exercise.entity.ExerciseEntity;

public interface CustomExerciseRepository {
    Page<ExerciseEntity> findAllByPage(Pageable pageable, Long routineId);
}
