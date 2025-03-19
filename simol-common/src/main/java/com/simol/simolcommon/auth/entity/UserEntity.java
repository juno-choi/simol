package com.simol.simolcommon.auth.entity;

import com.simol.simolcommon.global.entity.GlobalEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "users")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserEntity extends GlobalEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;
    private String name;
    private String profileImage;
    private String role;

    @Builder
    public UserEntity(Long id, String email, String name, String profileImage, String role) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.profileImage = profileImage;
        this.role = role;
    }

    public static UserEntity create(String email, String name, String profileImage, String role) {
        return UserEntity.builder()
            .email(email)
            .name(name)
            .profileImage(profileImage)
            .role(role)
            .build();
    }
}
