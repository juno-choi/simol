package com.simol.simolcommon.exercise.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.simol.simolcommon.exercise.entity.ExerciseEntity;

public interface ExerciseRepository extends JpaRepository<ExerciseEntity, Long>, CustomExerciseRepository {
    
}
