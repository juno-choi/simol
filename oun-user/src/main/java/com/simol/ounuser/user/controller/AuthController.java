package com.simol.ounuser.user.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.simol.ouncommon.api.CommonApi;
import com.simol.ouncommon.user.dto.OAuthRequest;
import com.simol.ounuser.user.service.AuthService;
import com.simol.ounuser.user.vo.RedirectUrlVo;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/user/auth")
@RequiredArgsConstructor
@Tag(name = "Auth", description = "인증 API")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/google")
    public ResponseEntity<?> googleLogin(@RequestBody OAuthRequest oauthRequest) {
        authService.authenticateWithGoogle(oauthRequest);
        return ResponseEntity.ok("ok");
    }

    @GetMapping("/google/url")
    @Operation(summary = "구글 리다이렉트 url 조회", description = "구글 리다이렉트 url 조회 API")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "유저 조회 성공"),
        @ApiResponse(responseCode = "400", description = "유저 조회 실패")
    })
    public ResponseEntity<CommonApi<RedirectUrlVo>> redirectUrlByGoogle(@RequestParam(name = "redirectUri") @Schema(description = "리다이렉트 URI", example = "http://localhost:3000/api/user/auth/google/callback") String redirectUri) {
        RedirectUrlVo redirectUrlVo = authService.redirectUrlByGoogle(redirectUri);
        return ResponseEntity.ok(CommonApi.of("0000", "success", redirectUrlVo));
    }
    
    
}
