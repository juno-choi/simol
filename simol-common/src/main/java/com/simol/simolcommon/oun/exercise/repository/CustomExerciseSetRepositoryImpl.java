package com.simol.simolcommon.oun.exercise.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.simol.simolcommon.exercise.entity.QExerciseSetEntity;
import com.simol.simolcommon.oun.exercise.entity.ExerciseSetEntity;
import com.simol.simolcommon.oun.exercise.enums.ExerciseSetStatus;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class CustomExerciseSetRepositoryImpl implements CustomExerciseSetRepository{
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<ExerciseSetEntity> findAllByExerciseIdPage(Pageable pageable, Long exerciseId) {
        QExerciseSetEntity exerciseSet = QExerciseSetEntity.exerciseSetEntity;
        BooleanBuilder builder = new BooleanBuilder();

        builder.and(exerciseSet.exercise.id.eq(exerciseId));
        builder.and(exerciseSet.status.eq(ExerciseSetStatus.ACTIVE));

        List<ExerciseSetEntity> fetchResults = jpaQueryFactory.selectFrom(exerciseSet)
            .where(builder)
            .offset(pageable.getOffset())
            .orderBy(exerciseSet.number.asc())
            .limit(pageable.getPageSize())
            .fetch();

        Long total = jpaQueryFactory.from(exerciseSet).where(builder).stream().count();

        return new PageImpl<>(fetchResults, pageable, total);
    }
    
}
