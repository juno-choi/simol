package com.simol.simolcommon.oun.exercise.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.simol.simolcommon.oun.exercise.entity.ExerciseEntity;

public interface ExerciseRepository extends JpaRepository<ExerciseEntity, Long>, CustomExerciseRepository {
    
}
