package com.simol.ouncommon.health.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.simol.ouncommon.health.entity.HealthEntity;
import com.simol.ouncommon.health.entity.QHealthEntity;
import com.simol.ouncommon.health.enums.HealthStatus;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class CustomHealthRepositoryImpl implements CustomHealthRepository{
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<HealthEntity> findAllByPage(Pageable pageable, Long routineId) {
        QHealthEntity health = QHealthEntity.healthEntity;
        BooleanBuilder builder = new BooleanBuilder();

        builder.and(health.routine.id.eq(routineId));
        builder.and(health.status.eq(HealthStatus.ACTIVE));

        List<HealthEntity> fetchResults = jpaQueryFactory.selectFrom(health)
            .where(builder)
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .orderBy(health.sort.asc())
            .fetch();

        Long total = jpaQueryFactory.from(health).where(builder).stream().count();

        return new PageImpl<>(fetchResults, pageable, total);
        

    }
    
}
