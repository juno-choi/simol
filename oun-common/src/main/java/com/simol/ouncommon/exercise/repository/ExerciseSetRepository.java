package com.simol.ouncommon.exercise.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.simol.ouncommon.exercise.entity.ExerciseSetEntity;

public interface ExerciseSetRepository extends JpaRepository<ExerciseSetEntity, Long>, CustomExerciseSetRepository {
    
}
