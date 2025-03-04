package com.simol.ouncommon.routine.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.simol.ouncommon.routine.entity.QRoutineEntity;
import com.simol.ouncommon.routine.entity.RoutineEntity;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class CustomRoutineRepositoryImpl implements CustomRoutineRepository {
    private final JPAQueryFactory query;

    @Override
    public Page<RoutineEntity> findAllByPage(Pageable pageable, Long userId) {
        QRoutineEntity routine = QRoutineEntity.routineEntity;
        BooleanBuilder builder = new BooleanBuilder();

        builder.and(routine.user.id.eq(userId));
        List<RoutineEntity> fetchResults = query.selectFrom(routine)
            .where(builder)
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetch();

        Long total = query.from(routine).where(builder).stream().count();

        return new PageImpl<>(fetchResults, pageable, total);
    }

}
