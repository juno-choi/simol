package com.simol.ounapi.exercise.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.simol.ouncommon.api.CommonApi;
import com.simol.ouncommon.api.ErrorApi;
import com.simol.ouncommon.exercise.dto.ExerciseCreateRequest;
import com.simol.ouncommon.exercise.dto.ExerciseUpdateRequest;
import com.simol.ouncommon.exercise.service.ExerciseService;
import com.simol.ouncommon.exercise.vo.ExerciseCreateResponse;
import com.simol.ouncommon.exercise.vo.ExerciseListResponse;
import com.simol.ouncommon.exercise.vo.ExerciseResponse;

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
@RequestMapping("/api/routine/exercise")
@RequiredArgsConstructor
@Tag(name = "02. Exercise", description = "운동 관리 API")
@SecurityRequirement(name = "X-User-Id")
@SecurityRequirement(name = "X-User-Role")
@SecurityRequirement(name = HttpHeaders.AUTHORIZATION)
public class ExerciseController {
    private final ExerciseService exerciseService;
    
    @PostMapping
    @Operation(summary = "1. 운동 생성", description = "운동을 생성합니다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "success", content = @Content(schema = @Schema(implementation = ExerciseCreateResponse.class))),
        @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = ErrorApi.class))),
    })
    public ResponseEntity<CommonApi<ExerciseCreateResponse>> createExercise(@RequestBody @Validated ExerciseCreateRequest exerciseCreateRequest, HttpServletRequest request, BindingResult bindingResult) {
        ExerciseCreateResponse exerciseCreateResponse = exerciseService.createExercise(exerciseCreateRequest, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(CommonApi.create(exerciseCreateResponse));
    }

    @GetMapping("/{exercise_id}")
    @Operation(summary = "2. 운동 조회", description = "운동을 조회합니다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "success", content = @Content(schema = @Schema(implementation = ExerciseResponse.class))),
        @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = ErrorApi.class))),
    })
    public ResponseEntity<CommonApi<ExerciseResponse>> getExercise(@Schema(description = "운동 id", example = "1") @PathVariable(name = "exercise_id") Long exerciseId) {
        ExerciseResponse exerciseResponse = exerciseService.getExercise(exerciseId);
        return ResponseEntity.ok(CommonApi.success(exerciseResponse));
    }

    @GetMapping("")
    @Operation(summary = "3. 운동 목록 조회", description = "운동 목록을 조회합니다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "success", content = @Content(schema = @Schema(implementation = ExerciseListResponse.class))),
        @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = ErrorApi.class))),
    })
    public ResponseEntity<CommonApi<ExerciseListResponse>> getExerciseList(
        @Schema(description = "routine ID", example = "1")
        @RequestParam(name = "routine_id") Long routineId,
        @Schema(description = "페이지", example = "0")
        @RequestParam(name = "page", defaultValue = "0") int page,
        @Schema(description = "페이지 크기", example = "10")
        @RequestParam(name = "size", defaultValue = "10") int size) {
        ExerciseListResponse exerciseListResponse = exerciseService.getExerciseList(routineId, page, size);
        return ResponseEntity.ok(CommonApi.success(exerciseListResponse));
    }

    @PutMapping("")
    @Operation(summary = "4. 운동 수정", description = "운동을 수정합니다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "success", content = @Content(schema = @Schema(implementation = ExerciseResponse.class))),
        @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = ErrorApi.class))),
    })
    public ResponseEntity<CommonApi<ExerciseResponse>> updateExercise(@RequestBody @Validated ExerciseUpdateRequest exerciseUpdateRequest, HttpServletRequest request, BindingResult bindingResult) {
        ExerciseResponse exerciseResponse = exerciseService.updateExercise(exerciseUpdateRequest, request);
        return ResponseEntity.ok(CommonApi.success(exerciseResponse));
    }
    
    @DeleteMapping("/{exercise_id}")
    @Operation(summary = "5. 운동 삭제", description = "운동을 삭제합니다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "success", content = @Content(schema = @Schema(implementation = ExerciseResponse.class))),
        @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = ErrorApi.class))),
    })
    public ResponseEntity<CommonApi<Void>> deleteExercise(@Schema(description = "운동 id", example = "1") @PathVariable(name = "exercise_id") Long exerciseId) {
        exerciseService.deleteExercise(exerciseId);
        return ResponseEntity.ok(CommonApi.success(null));
    }
}
