package com.simol.ounapi.routine.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;

import com.simol.ounapi.fixture.EntityFixtures;
import com.simol.ounapi.fixture.RequestFixtures;
import com.simol.ouncommon.auth.entity.UserEntity;
import com.simol.ouncommon.auth.repository.UsersRepository;
import com.simol.ouncommon.exception.BadRequestException;
import com.simol.ouncommon.routine.dto.RoutineCreateRequest;
import com.simol.ouncommon.routine.entity.RoutineEntity;
import com.simol.ouncommon.routine.repository.RoutineRepository;
import com.simol.ouncommon.routine.vo.RoutineCreateResponse;
import com.simol.ouncommon.routine.vo.RoutineResponse;

import jakarta.servlet.http.HttpServletRequest;

@SpringBootTest
public class RoutineServiceImplTest {
    @Autowired
    private RoutineServiceImpl routineService;
    @Autowired
    private RoutineRepository routineRepository;
    @Autowired
    private UsersRepository usersRepository;

    private HttpServletRequest request = new MockHttpServletRequest();

    @AfterEach
    void cleanUp() {
        routineRepository.deleteAll();
        usersRepository.deleteAll();
    }

    @Test
    @DisplayName("user가 존재하지 않으면 루틴 생성 실패")
    void createRoutineFail1() {
        // given
        RoutineCreateRequest routineCreateRequest = RequestFixtures.aRoutineCreateRequest()
            .build();

        request.setAttribute("userId", 0L);

        // when then
        Assertions.assertThatExceptionOfType(BadRequestException.class)
            .isThrownBy(() -> routineService.createRoutine(routineCreateRequest, request))
            .withMessageContaining("User not found");
    }

    @Test
    @DisplayName("루틴 생성 성공")
    void createRoutineSuccess() {
        // given
        UserEntity user = EntityFixtures.aUser()
            .build();

        UserEntity saveUser = usersRepository.save(user);

        RoutineCreateRequest routineCreateRequest = RequestFixtures.aRoutineCreateRequest()
            .build();

        request.setAttribute("userId", saveUser.getId());

        // when
        RoutineCreateResponse routineCreateResponse = routineService.createRoutine(routineCreateRequest, request);

        // then
        Assertions.assertThat(routineCreateResponse).isNotNull();
        Assertions.assertThat(routineCreateResponse.getRoutineId()).isNotNull();
        Assertions.assertThat(routineCreateResponse.getName()).isEqualTo("test");
    }

    @Test
    @DisplayName("루틴 조회 실패")
    void getRoutineFail1() {
        // given
        final Long routineId = 1L;

        // when then
        Assertions.assertThatExceptionOfType(BadRequestException.class)
            .isThrownBy(() -> routineService.getRoutine(routineId))
            .withMessageContaining("Routine not found");
    }

    @Test
    @DisplayName("루틴 조회 성공")
    void getRoutineSuccess() {
        // given
        UserEntity user = EntityFixtures.aUser()
            .build();

        UserEntity saveUser = usersRepository.save(user);

        RoutineCreateRequest routineCreateRequest = RequestFixtures.aRoutineCreateRequest()
            .build();

        RoutineEntity routine = routineRepository.save(RoutineEntity.create(routineCreateRequest, saveUser));
        
        // when
        RoutineResponse routineResponse = routineService.getRoutine(routine.getId());

        // then
        Assertions.assertThat(routineResponse).isNotNull();
        Assertions.assertThat(routineResponse.getRoutineId()).isEqualTo(routine.getId());
        Assertions.assertThat(routineResponse.getName()).isEqualTo("test");
    }
}
