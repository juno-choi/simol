package com.simol.simolcommon.oun.routine.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.simol.simolcommon.oun.routine.entity.RoutineEntity;
import com.simol.simolcommon.oun.routine.enums.RoutineStatus;
import com.simol.simolcommon.routine.entity.QRoutineEntity;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class CustomRoutineRepositoryImpl implements CustomRoutineRepository {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<RoutineEntity> findAllByPage(Pageable pageable, Long userId) {
        QRoutineEntity routine = QRoutineEntity.routineEntity;
        BooleanBuilder builder = new BooleanBuilder();

        builder.and(routine.user.id.eq(userId));
        builder.and(routine.status.eq(RoutineStatus.ACTIVE));

        List<RoutineEntity> fetchResults = jpaQueryFactory.selectFrom(routine)
            .where(builder)
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .orderBy(routine.createdAt.desc())
            .fetch();

        Long total = jpaQueryFactory.from(routine).where(builder).stream().count();

        return new PageImpl<>(fetchResults, pageable, total);
    }

}
