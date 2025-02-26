package com.simol.ounapi.test.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.simol.ounapi.test.domain.dto.TestValidationRequest;
import com.simol.ouncommon.api.CommonApi;
import com.simol.ouncommon.api.ErrorApi;
import com.simol.ouncommon.exception.BadRequestException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/test")
@Tag(name = "테스트 API")
@Slf4j
public class TestController {
    
    @GetMapping
    @Operation(summary = "테스트 API", description = "테스트 API")
    public ResponseEntity<CommonApi<String>> test() {
        return ResponseEntity.ok(CommonApi.of("0000", "success", "test"));
    }

    @GetMapping("/error")
    @Operation(summary = "에러 테스트 API", description = "에러 테스트 API")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "성공", content = @Content(schema = @Schema(implementation = CommonApi.class))),
        @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = ErrorApi.class))),
        
    })
    public ResponseEntity<CommonApi<String>> error() {
        String test = "A";
        if ("A".equals(test)) {
            throw new BadRequestException("test");
        }
        return ResponseEntity.ok(CommonApi.of("0000", "success", "test"));
    }

    @GetMapping("/validation")
    public ResponseEntity<CommonApi<String>> failValidation(
        @Validated @ModelAttribute TestValidationRequest testValidationRequest
    ) {
        return ResponseEntity.ok(CommonApi.of("0000", "success", "test"));
    }

    @PostMapping("/validation")
    public ResponseEntity<CommonApi<String>> failValidation2(
        @Validated @RequestBody TestValidationRequest testValidationRequest
    ) {
        log.info("testValidationRequest: {}, {}", testValidationRequest.getName(), testValidationRequest.getAge());
        return ResponseEntity.ok(CommonApi.of("0000", "success", "test"));
    }
    
}
