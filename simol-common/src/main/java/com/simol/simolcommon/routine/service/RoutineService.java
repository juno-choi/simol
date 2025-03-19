package com.simol.simolcommon.routine.service;

import com.simol.simolcommon.routine.dto.RoutineCreateRequest;
import com.simol.simolcommon.routine.dto.RoutineUpdateRequest;
import com.simol.simolcommon.routine.vo.RoutineCreateResponse;
import com.simol.simolcommon.routine.vo.RoutineListResponse;
import com.simol.simolcommon.routine.vo.RoutineResponse;

import jakarta.servlet.http.HttpServletRequest;

public interface RoutineService {
    RoutineCreateResponse createRoutine(RoutineCreateRequest routineCreateRequest, HttpServletRequest request);
    RoutineResponse getRoutine(Long routineId);
    RoutineListResponse getRoutineList(int page, int size, HttpServletRequest request);
    RoutineResponse updateRoutine(RoutineUpdateRequest routineUpdateRequest, HttpServletRequest request);
    void deleteRoutine(Long routineId, HttpServletRequest request);
}
