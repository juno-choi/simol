package com.simol.simoluser.user.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.simol.simolcommon.common.api.CommonApi;
import com.simol.simolcommon.common.auth.vo.AuthTokenResponse;
import com.simol.simoluser.user.service.AuthService;
import com.simol.simoluser.user.vo.RedirectUrlResponse;
import com.simol.simoluser.user.vo.UserInfoResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/user/auth")
@RequiredArgsConstructor
@Tag(name = "Auth", description = "인증 API")
public class AuthController {
    private final AuthService authService;

    @GetMapping("/google")
    @Operation(summary = "구글 인증 정보 확인", description = "구글 인증 정보 확인 API")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "성공"),
        @ApiResponse(responseCode = "400", description = "실패"),
        @ApiResponse(responseCode = "500", description = "서버 오류"),
    })
    public ResponseEntity<CommonApi<AuthTokenResponse>> googleLogin(@RequestParam(name = "access_token") @Schema(description = "액세스 토큰", example = "1234567890") String accessToken) {
        AuthTokenResponse authTokenResponse = authService.authenticateWithGoogle(accessToken);
        return ResponseEntity.ok(CommonApi.of("0000", "success", authTokenResponse));
    }

    @GetMapping("/google/url")
    @Operation(summary = "구글 리다이렉트 url 조회", description = "구글 리다이렉트 url 조회 API")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "성공"),
        @ApiResponse(responseCode = "400", description = "실패"),
        @ApiResponse(responseCode = "500", description = "서버 오류"),
    })
    public ResponseEntity<CommonApi<RedirectUrlResponse>> redirectUrlByGoogle(@RequestParam(name = "redirect_uri") @Schema(description = "리다이렉트 URI", example = "http://localhost:3000/api/user/auth/google/callback") String redirectUri) {
        RedirectUrlResponse redirectUrlVo = authService.redirectUrlByGoogle(redirectUri);
        return ResponseEntity.ok(CommonApi.of("0000", "success", redirectUrlVo));
    }

    @GetMapping("/refresh/{refresh_token}")
    @Operation(summary = "토큰 재발급 (refresh token)", description = "refresh token을 사용하여 토큰 재발급 API")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "성공"),
        @ApiResponse(responseCode = "400", description = "실패"),
        @ApiResponse(responseCode = "500", description = "서버 오류"),
    })
    public ResponseEntity<CommonApi<AuthTokenResponse>> refreshToken(@PathVariable(name = "refresh_token") @Schema(description = "발급 받은 refresh token", example = "REFRESH_TOKEN") String refreshToken) {
        AuthTokenResponse authTokenResponse = authService.refreshToken(refreshToken);
        return ResponseEntity.ok(CommonApi.of("0000", "success", authTokenResponse));
    }
    
    @GetMapping("/info/{access_token}")
    @Operation(summary = "user 정보 조회 (access token)", description = "access token을 사용하여 유저 정보 조회 API")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "성공"),
        @ApiResponse(responseCode = "400", description = "실패"),
        @ApiResponse(responseCode = "500", description = "서버 오류"),
    })
    public ResponseEntity<CommonApi<UserInfoResponse>> userInfo(@PathVariable(name = "access_token") @Schema(description = "access_token", example = "ACCESS_TOKEN") String accessToken) {
        UserInfoResponse userInfo = authService.getUserInfo(accessToken);
        return ResponseEntity.ok(CommonApi.of("0000", "success", userInfo));
    }

    @GetMapping("/unauthorized")
    @Operation(summary = "401 에러 테스트", description = "401 에러 테스트 API")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "성공"),
        @ApiResponse(responseCode = "400", description = "실패"),
        @ApiResponse(responseCode = "500", description = "서버 오류"),
    })
    public ResponseEntity<CommonApi<String>> unauthorized() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(CommonApi.of("0401", "success", "unauthorized"));
    }
    
    @GetMapping("")
    @Operation(summary = "검증 end point api", description = "token에 대한 검증 처리 api 입니다. header에 userId와 userRole를 추가하여 반환합니다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "성공"),
        @ApiResponse(responseCode = "400", description = "실패"),
        @ApiResponse(responseCode = "500", description = "서버 오류"),
    })
    public ResponseEntity<Void> auth(HttpServletRequest request, HttpServletResponse response) {
        authService.getUserInfo(request, response);
        return ResponseEntity.ok().build();
    }

}
