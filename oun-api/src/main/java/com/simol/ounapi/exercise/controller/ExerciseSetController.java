package com.simol.ounapi.exercise.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.simol.simolcommon.api.CommonApi;
import com.simol.simolcommon.api.ErrorApi;
import com.simol.simolcommon.oun.exercise.dto.ExerciseSetCreateRequest;
import com.simol.simolcommon.oun.exercise.dto.ExerciseSetUpdateRequest;
import com.simol.simolcommon.oun.exercise.service.ExerciseSetService;
import com.simol.simolcommon.oun.exercise.vo.ExerciseSetCreateResponse;
import com.simol.simolcommon.oun.exercise.vo.ExerciseSetListResponse;
import com.simol.simolcommon.oun.exercise.vo.ExerciseSetResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;    
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/oun/routine/exercise/set")
@Tag(name = "03. ExerciseSet", description = "운동 세트 정보 API")
@RequiredArgsConstructor
public class ExerciseSetController {
    private final ExerciseSetService exerciseSetService;

    @PostMapping
    @Operation(summary = "1. 운동 세트 생성", description = "운동 세트를 생성합니다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "운동 세트 생성 성공", content = @Content(schema = @Schema(implementation = ExerciseSetCreateResponse.class))),
        @ApiResponse(responseCode = "400", description = "운동 세트 생성 실패", content = @Content(schema = @Schema(implementation = ErrorApi.class)))
    })
    public ResponseEntity<CommonApi<ExerciseSetCreateResponse>> createExerciseSet(@RequestBody @Validated ExerciseSetCreateRequest exerciseSetCreateRequest, HttpServletRequest request) {
        ExerciseSetCreateResponse exerciseSetCreateResponse = exerciseSetService.createExerciseSet(exerciseSetCreateRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(CommonApi.create(exerciseSetCreateResponse));
    }

    @GetMapping("/{exercise_set_id}")
    @Operation(summary = "2. 운동 세트 조회", description = "운동 세트를 조회합니다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "운동 세트 조회 성공", content = @Content(schema = @Schema(implementation = ExerciseSetResponse.class))),
        @ApiResponse(responseCode = "400", description = "운동 세트 조회 실패", content = @Content(schema = @Schema(implementation = ErrorApi.class)))
    })
    public ResponseEntity<CommonApi<ExerciseSetResponse>> getExerciseSet(@PathVariable(name = "exercise_set_id") Long exerciseSetId) {
        ExerciseSetResponse exerciseSetResponse = exerciseSetService.getExerciseSet(exerciseSetId);
        return ResponseEntity.ok(CommonApi.success(exerciseSetResponse));
    }
 
    @GetMapping("")
    @Operation(summary = "3. 운동 세트 목록 조회", description = "운동 세트 목록을 조회합니다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "운동 세트 목록 조회 성공", content = @Content(schema = @Schema(implementation = ExerciseSetListResponse.class))),
        @ApiResponse(responseCode = "400", description = "운동 세트 목록 조회 실패", content = @Content(schema = @Schema(implementation = ErrorApi.class)))
    })
    public ResponseEntity<CommonApi<ExerciseSetListResponse>> getExerciseSetList(
        @Schema(description = "운동 아이디", example = "1")
        @RequestParam(name = "exercise_id") Long exerciseId, 
        @Schema(description = "페이지", example = "0")
        @RequestParam(name = "page", defaultValue = "0") int page, 
        @Schema(description = "페이지 크기", example = "10")
        @RequestParam(name = "size", defaultValue = "10") int size) {
        ExerciseSetListResponse exerciseSetListResponse = exerciseSetService.getExerciseSetList(page, size, exerciseId);
        return ResponseEntity.ok(CommonApi.success(exerciseSetListResponse));
    }

    @PutMapping("")
    @Operation(summary = "4. 운동 세트 수정", description = "운동 세트를 수정합니다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "운동 세트 수정 성공", content = @Content(schema = @Schema(implementation = ExerciseSetResponse.class))),
        @ApiResponse(responseCode = "400", description = "운동 세트 수정 실패", content = @Content(schema = @Schema(implementation = ErrorApi.class)))
    })
    public ResponseEntity<CommonApi<ExerciseSetResponse>> updateExerciseSet(@RequestBody @Validated ExerciseSetUpdateRequest exerciseSetUpdateRequest) {
        ExerciseSetResponse exerciseSetResponse = exerciseSetService.updateExerciseSet(exerciseSetUpdateRequest);
        return ResponseEntity.ok(CommonApi.success(exerciseSetResponse));
    }
    

}
