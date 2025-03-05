package com.simol.ouncommon.healthset.repository;

import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class CustomHealthSetRepositoryImpl implements CustomHealthSetRepository{
    private final JPAQueryFactory jpaQueryFactory;
}
