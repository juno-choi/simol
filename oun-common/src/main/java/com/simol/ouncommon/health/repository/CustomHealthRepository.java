package com.simol.ouncommon.health.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.simol.ouncommon.health.entity.HealthEntity;

public interface CustomHealthRepository {
    Page<HealthEntity> findAllByPage(Pageable pageable, Long userId);
}
