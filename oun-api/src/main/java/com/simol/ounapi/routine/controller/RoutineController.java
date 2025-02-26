package com.simol.ounapi.routine.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.simol.ouncommon.api.CommonApi;
import com.simol.ouncommon.api.ErrorApi;
import com.simol.ouncommon.routine.dto.RoutineCreateRequest;
import com.simol.ouncommon.routine.service.RoutineService;
import com.simol.ouncommon.routine.vo.RoutineCreateResponse;
import com.simol.ouncommon.routine.vo.RoutineResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/routine")
@RequiredArgsConstructor
@Tag(name = "01. Routine", description = "루틴 API")
@SecurityRequirement(name = "bearerAuth")  // swagger security 설정
public class RoutineController {
    private final RoutineService routineService;

    @PostMapping
    @Operation(summary = "1. routine 생성", description = "루틴을 생성합니다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "success", content = @Content(schema = @Schema(implementation = RoutineCreateResponse.class))),
        @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = ErrorApi.class))),
    })
    public ResponseEntity<CommonApi<RoutineCreateResponse>> createRoutine(
        @RequestBody RoutineCreateRequest routineCreateRequest, HttpServletRequest request
    ) {
        RoutineCreateResponse routineCreateResponse = routineService.createRoutine(routineCreateRequest, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(CommonApi.create(routineCreateResponse));
    }

    @GetMapping("/{routine_id}")
    @Operation(summary = "2. routine 조회", description = "루틴을 조회합니다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "success", content = @Content(schema = @Schema(implementation = RoutineResponse.class))),
        @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = ErrorApi.class))),
    })
    public ResponseEntity<CommonApi<RoutineResponse>> getRoutine(
        @PathVariable(name = "routine_id") Long routineId
    ) {
        RoutineResponse routineResponse = routineService.getRoutine(routineId);
        return ResponseEntity.ok(CommonApi.success(routineResponse));
    }
}
