package com.simol.ounapi.fixture;

import com.simol.simolcommon.common.auth.entity.UserEntity;

public class EntityFixtures {
    public static UserEntity.UserEntityBuilder aUser() {
        return UserEntity.builder()
            .email("test@test.com")
            .name("test")
            .profileImage("https://test.com/test.jpg")
            .role("ROLE_USER")
            ;
    }
}
