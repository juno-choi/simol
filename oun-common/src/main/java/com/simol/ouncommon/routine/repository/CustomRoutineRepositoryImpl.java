package com.simol.ouncommon.routine.repository;

import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class CustomRoutineRepositoryImpl implements CustomRoutineRepository {
    private final JPAQueryFactory query;

    @Override
    public String test() {
        return "test";
    }
}
