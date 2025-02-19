package com.simol.ounapi.test;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.simol.ouncommon.api.CommonApi;
import com.simol.ouncommon.api.ErrorApi;
import com.simol.ouncommon.exception.BadRequestException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/test")
@Tag(name = "테스트 API")
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
        @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = ErrorApi.class)))
    })
    public ResponseEntity<CommonApi<String>> error() {
        if (true) {
            throw new BadRequestException("test");
        }
        return ResponseEntity.ok(CommonApi.of("0000", "success", "test"));
    }
}
