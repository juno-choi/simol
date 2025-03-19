package com.simol.simoluser.user.vo;

import com.simol.simolcommon.auth.entity.UserEntity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Schema(description = "유저 정보")
public class UserInfoResponse {
    @Schema(description = "유저 아이디", example = "1")
    private Long id;
    @Schema(description = "유저 이메일", example = "test@test.com")
    private String email;
    @Schema(description = "유저 이름", example = "홍길동")
    private String name;
    @Schema(description = "유저 프로필 이미지", example = "https://example.com/profile.jpg")
    private String profileImage;

    public static UserInfoResponse from(UserEntity user) {
        return UserInfoResponse.builder()
            .id(user.getId())
            .email(user.getEmail())
            .name(user.getName())
            .profileImage(user.getProfileImage())
            .build();
    }
}
