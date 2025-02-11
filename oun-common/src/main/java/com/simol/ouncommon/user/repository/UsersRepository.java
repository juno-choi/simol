package com.simol.ouncommon.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.simol.ouncommon.user.entity.UserEntity;

public interface UsersRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByEmail(String email);

}
