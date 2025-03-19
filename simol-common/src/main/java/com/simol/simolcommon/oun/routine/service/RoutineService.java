package com.simol.simolcommon.oun.routine.service;

import com.simol.simolcommon.oun.routine.dto.RoutineCreateRequest;
import com.simol.simolcommon.oun.routine.dto.RoutineUpdateRequest;
import com.simol.simolcommon.oun.routine.vo.RoutineCreateResponse;
import com.simol.simolcommon.oun.routine.vo.RoutineListResponse;
import com.simol.simolcommon.oun.routine.vo.RoutineResponse;

import jakarta.servlet.http.HttpServletRequest;

public interface RoutineService {
    RoutineCreateResponse createRoutine(RoutineCreateRequest routineCreateRequest, HttpServletRequest request);
    RoutineResponse getRoutine(Long routineId);
    RoutineListResponse getRoutineList(int page, int size, HttpServletRequest request);
    RoutineResponse updateRoutine(RoutineUpdateRequest routineUpdateRequest, HttpServletRequest request);
    void deleteRoutine(Long routineId, HttpServletRequest request);
}
