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
import com.simol.simolcommon.common.auth.entity.UserEntity;
import com.simol.simolcommon.common.auth.repository.UsersRepository;
import com.simol.simolcommon.common.exception.BadRequestException;
import com.simol.simolcommon.oun.routine.dto.RoutineCreateRequest;
import com.simol.simolcommon.oun.routine.dto.RoutineUpdateRequest;
import com.simol.simolcommon.oun.routine.entity.RoutineEntity;
import com.simol.simolcommon.oun.routine.enums.RoutineStatus;
import com.simol.simolcommon.oun.routine.repository.RoutineRepository;
import com.simol.simolcommon.oun.routine.vo.RoutineCreateResponse;
import com.simol.simolcommon.oun.routine.vo.RoutineListResponse;
import com.simol.simolcommon.oun.routine.vo.RoutineResponse;

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
    @DisplayName("routine이 존재하지 않으면 루틴 조회 실패")
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

    @Test
    @DisplayName("routine list 조회 성공")
    void getRoutineListSuccess() {
        // given
        UserEntity user = EntityFixtures.aUser()
            .build();

        UserEntity saveUser = usersRepository.save(user);

        RoutineCreateRequest routineCreateRequest = RequestFixtures.aRoutineCreateRequest()
            .build();

        routineRepository.save(RoutineEntity.create(routineCreateRequest, saveUser));
        request.setAttribute("userId", saveUser.getId());

        // when
        RoutineListResponse routineListResponse = routineService.getRoutineList(0, 10, request);

        // then
        Assertions.assertThat(routineListResponse).isNotNull();
        Assertions.assertThat(routineListResponse.getRoutineList()).isNotEmpty();
    }

    @Test
    @DisplayName("루틴 id 값이 존재하지 않으면 루틴 수정 실패")
    void updateRoutineFail1() {
        // given
        RoutineUpdateRequest routineUpdateRequest = RequestFixtures.aRoutineUpdateRequest()
            .build();

        request.setAttribute("userId", 0L);

        // when then
        Assertions.assertThatExceptionOfType(BadRequestException.class)
            .isThrownBy(() -> routineService.updateRoutine(routineUpdateRequest, request))
            .withMessageContaining("Routine not found");
    }

    @Test
    @DisplayName("루틴 id값이랑 요청 user id가 다르면 루틴 수정 실패")
    void updateRoutineFail2() {
        // given
        UserEntity user = EntityFixtures.aUser()
            .build();
        UserEntity user2 = EntityFixtures.aUser()
            .email("test2@test.com")
            .build();

        UserEntity saveUser = usersRepository.save(user);
        UserEntity saveUser2 = usersRepository.save(user2);

        RoutineCreateRequest routineCreateRequest = RequestFixtures.aRoutineCreateRequest()
            .build();

        RoutineEntity routine = routineRepository.save(RoutineEntity.create(routineCreateRequest, saveUser));

        RoutineUpdateRequest routineUpdateRequest = RequestFixtures.aRoutineUpdateRequest()
            .routineId(routine.getId())
            .build();

        request.setAttribute("userId", saveUser2.getId());

        // when then
        Assertions.assertThatExceptionOfType(BadRequestException.class)
            .isThrownBy(() -> routineService.updateRoutine(routineUpdateRequest, request))
            .withMessageContaining("User Routine not found");
    }

    @Test
    @DisplayName("루틴 수정 성공")
    void updateRoutineSuccess() {
        // given
        UserEntity user = EntityFixtures.aUser()
            .build();

        UserEntity saveUser = usersRepository.save(user);

        RoutineCreateRequest routineCreateRequest = RequestFixtures.aRoutineCreateRequest()
            .build();

        RoutineEntity routine = routineRepository.save(RoutineEntity.create(routineCreateRequest, saveUser));

        RoutineUpdateRequest routineUpdateRequest = RequestFixtures.aRoutineUpdateRequest()
            .routineId(routine.getId())
            .build();

        request.setAttribute("userId", saveUser.getId());

        // when
        RoutineResponse routineResponse = routineService.updateRoutine(routineUpdateRequest, request);

        // then
        Assertions.assertThat(routineResponse).isNotNull();
        Assertions.assertThat(routineResponse.getRoutineId()).isEqualTo(routine.getId());
        Assertions.assertThat(routineResponse.getName()).isEqualTo("test");
    }

    @Test
    @DisplayName("루틴 id 값이 존재하지 않으면 루틴 수정 실패")
    void deleteRoutineFail1() {
        // given
        final Long routineId = 1L;

        request.setAttribute("userId", 0L);

        // when then
        Assertions.assertThatExceptionOfType(BadRequestException.class)
            .isThrownBy(() -> routineService.deleteRoutine(routineId, request))
            .withMessageContaining("Routine not found");
    }

    @Test
    @DisplayName("루틴 id값이랑 요청 user id가 다르면 루틴 삭제 실패")
    void deleteRoutineFail2() {
        // given
        UserEntity user = EntityFixtures.aUser()
            .build();
        UserEntity user2 = EntityFixtures.aUser()
            .email("test2@test.com")
            .build();

        UserEntity saveUser = usersRepository.save(user);
        UserEntity saveUser2 = usersRepository.save(user2);
        
        RoutineCreateRequest routineCreateRequest = RequestFixtures.aRoutineCreateRequest()
            .build();

        RoutineEntity routine = routineRepository.save(RoutineEntity.create(routineCreateRequest, saveUser));

        request.setAttribute("userId", saveUser2.getId());

        // when then
        Assertions.assertThatExceptionOfType(BadRequestException.class)
            .isThrownBy(() -> routineService.deleteRoutine(routine.getId(), request))
            .withMessageContaining("User Routine not found");
    }

    @Test
    @DisplayName("루틴 삭제 성공")
    void deleteRoutineSuccess() {
        // given
        UserEntity user = EntityFixtures.aUser()
            .build();
        UserEntity saveUser = usersRepository.save(user);
        RoutineCreateRequest routineCreateRequest = RequestFixtures.aRoutineCreateRequest()
            .build();
        RoutineEntity routine = routineRepository.save(RoutineEntity.create(routineCreateRequest, saveUser));

        request.setAttribute("userId", saveUser.getId());

        // when
        routineService.deleteRoutine(routine.getId(), request);

        // then
        RoutineEntity findRoutine = routineRepository.findById(routine.getId()).get();
        Assertions.assertThat(findRoutine.getStatus()).isEqualTo(RoutineStatus.INACTIVE);
    }
}
