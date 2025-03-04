package com.simol.ouncommon.global.config.querydsl;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.querydsl.jpa.impl.JPAQueryFactory;

import jakarta.persistence.EntityManager;

@Configuration
public class ApiQuerydslConfig {
    
    @Bean
    public JPAQueryFactory query(EntityManager em) {
        return new JPAQueryFactory(em);
    }
}
