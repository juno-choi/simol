package com.simol.ouncommon.healthset.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.simol.ouncommon.health.entity.QHealthSetEntity;
import com.simol.ouncommon.healthset.entity.HealthSetEntity;
import com.simol.ouncommon.healthset.enums.HealthSetStatus;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class CustomHealthSetRepositoryImpl implements CustomHealthSetRepository{
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<HealthSetEntity> findAllByHealthIdPage(Pageable pageable, Long healthId) {
        QHealthSetEntity healthSet = QHealthSetEntity.healthSetEntity;
        BooleanBuilder builder = new BooleanBuilder();

        builder.and(healthSet.health.id.eq(healthId));
        builder.and(healthSet.status.eq(HealthSetStatus.ACTIVE));

        List<HealthSetEntity> fetchResults = jpaQueryFactory.selectFrom(healthSet)
            .where(builder)
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .orderBy(healthSet.sort.asc())
            .fetch();

        Long total = jpaQueryFactory.from(healthSet).where(builder).stream().count();

        return new PageImpl<>(fetchResults, pageable, total);
    }
    
}
