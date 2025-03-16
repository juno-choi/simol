package com.simol.ouncommon.exercise.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.simol.ouncommon.exercise.entity.ExerciseEntity;

public interface ExerciseRepository extends JpaRepository<ExerciseEntity, Long>, CustomExerciseRepository {
    
}
