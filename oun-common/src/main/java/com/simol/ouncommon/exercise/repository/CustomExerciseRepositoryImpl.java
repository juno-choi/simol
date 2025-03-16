package com.simol.ouncommon.exercise.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.simol.ouncommon.exercise.entity.ExerciseEntity;
import com.simol.ouncommon.exercise.entity.QExerciseEntity;
import com.simol.ouncommon.exercise.enums.ExerciseStatus;
import com.simol.ouncommon.exercise.entity.QExerciseSetEntity;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class CustomExerciseRepositoryImpl implements CustomExerciseRepository{
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<ExerciseEntity> findAllByPage(Pageable pageable, Long routineId) {
        QExerciseEntity exercise = QExerciseEntity.exerciseEntity;
        QExerciseSetEntity exerciseSet = QExerciseSetEntity.exerciseSetEntity;
        BooleanBuilder builder = new BooleanBuilder();

        builder.and(exercise.routine.id.eq(routineId));
        builder.and(exercise.status.eq(ExerciseStatus.ACTIVE));

        List<ExerciseEntity> fetchResults = jpaQueryFactory.selectFrom(exercise)
            .leftJoin(exercise.exerciseSetList, exerciseSet).on(exerciseSet.exercise.id.eq(exercise.id))
            .where(builder)
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .orderBy(exercise.sort.asc())
            .fetch();

        Long total = jpaQueryFactory.from(exercise).where(builder).stream().count();

        return new PageImpl<>(fetchResults, pageable, total);
        

    }
    
}
