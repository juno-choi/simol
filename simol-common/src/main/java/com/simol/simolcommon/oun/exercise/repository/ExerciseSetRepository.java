package com.simol.simolcommon.oun.exercise.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.simol.simolcommon.oun.exercise.entity.ExerciseSetEntity;

public interface ExerciseSetRepository extends JpaRepository<ExerciseSetEntity, Long>, CustomExerciseSetRepository {
    
}
