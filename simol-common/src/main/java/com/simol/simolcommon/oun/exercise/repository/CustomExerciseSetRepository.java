package com.simol.simolcommon.oun.exercise.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.simol.simolcommon.oun.exercise.entity.ExerciseSetEntity;

public interface CustomExerciseSetRepository {
    Page<ExerciseSetEntity> findAllByExerciseIdPage(Pageable pageable, Long exerciseId);
}
