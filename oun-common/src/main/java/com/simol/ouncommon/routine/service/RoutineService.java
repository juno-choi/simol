package com.simol.ouncommon.routine.service;

import org.springframework.data.domain.Pageable;

import com.simol.ouncommon.routine.dto.RoutineCreateRequest;
import com.simol.ouncommon.routine.vo.RoutineCreateResponse;
import com.simol.ouncommon.routine.vo.RoutineListResponse;
import com.simol.ouncommon.routine.vo.RoutineResponse;

import jakarta.servlet.http.HttpServletRequest;

public interface RoutineService {
    RoutineCreateResponse createRoutine(RoutineCreateRequest routineCreateRequest, HttpServletRequest request);
    RoutineResponse getRoutine(Long routineId);
    RoutineListResponse getRoutineList(int page, int size, HttpServletRequest request);
}
