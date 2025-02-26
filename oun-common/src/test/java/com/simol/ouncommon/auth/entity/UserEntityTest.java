package com.simol.ouncommon.auth.entity;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class UserEntityTest {
    @Test
    @DisplayName("UserEntity 생성 테스트")
    void createSuccess() {
        UserEntity user = UserEntity.create("test@test.com", "test", "test", "test");

        Assertions.assertThat(user.getEmail()).isEqualTo("test@test.com");
        Assertions.assertThat(user.getName()).isEqualTo("test");
        Assertions.assertThat(user.getProfileImage()).isEqualTo("test");
        Assertions.assertThat(user.getRole()).isEqualTo("test");
    }
}
