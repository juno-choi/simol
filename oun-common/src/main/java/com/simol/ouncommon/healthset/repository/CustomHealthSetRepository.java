package com.simol.ouncommon.healthset.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.simol.ouncommon.healthset.entity.HealthSetEntity;

public interface CustomHealthSetRepository {
    Page<HealthSetEntity> findAllByHealthIdPage(Pageable pageable, Long healthId);
}
