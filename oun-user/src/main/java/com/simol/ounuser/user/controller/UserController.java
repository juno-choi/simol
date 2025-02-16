package com.simol.ounuser.user.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/users")
@Tag(name = "User", description = "유저 API")
public class UserController {

    @Operation(summary = "유저 조회", description = "유저 조회 API")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "유저 조회 성공"),
        @ApiResponse(responseCode = "400", description = "유저 조회 실패")
    })
    @GetMapping
    public String getUsers() {
        return "Hello, User!";
    }
}