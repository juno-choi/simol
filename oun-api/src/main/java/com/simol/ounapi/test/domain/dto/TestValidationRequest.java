package com.simol.ounapi.test.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TestValidationRequest {
    @NotNull(message = "이름은 필수 입력 사항입니다.")
    @Schema(description = "이름", example = "홍길동")
    private String name;
    @Min(value = 10, message = "10 이상이어야 합니다.")
    @Schema(description = "나이", example = "10")
    private int age;
}
