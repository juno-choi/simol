package com.simol.simolcommon.exercise.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.simol.simolcommon.exercise.entity.ExerciseSetEntity;

public interface ExerciseSetRepository extends JpaRepository<ExerciseSetEntity, Long>, CustomExerciseSetRepository {
    
}
