package com.simol.ounapi.exercise.service;

import java.util.Optional;

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
import com.simol.ouncommon.exercise.dto.ExerciseCreateRequest;
import com.simol.ouncommon.exercise.dto.ExerciseUpdateRequest;
import com.simol.ouncommon.exercise.entity.ExerciseEntity;
import com.simol.ouncommon.exercise.enums.ExerciseStatus;
import com.simol.ouncommon.exercise.repository.ExerciseRepository;
import com.simol.ouncommon.exercise.vo.ExerciseCreateResponse;
import com.simol.ouncommon.exercise.vo.ExerciseResponse;
import com.simol.ouncommon.exercise.vo.ExerciseListResponse;
import com.simol.ouncommon.routine.dto.RoutineCreateRequest;
import com.simol.ouncommon.routine.entity.RoutineEntity;
import com.simol.ouncommon.routine.repository.RoutineRepository;

import jakarta.servlet.http.HttpServletRequest;

@SpringBootTest
public class ExerciseServiceImplTest {
    @Autowired
    private ExerciseServiceImpl exerciseService;

    @Autowired
    private ExerciseRepository exerciseRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private RoutineRepository routineRepository;

    private HttpServletRequest request = new MockHttpServletRequest();

    @AfterEach
    void cleanUp() {
        exerciseRepository.deleteAll();
        routineRepository.deleteAll();
        usersRepository.deleteAll();
    }

    @Test
    @DisplayName("user가 존재하지 않으면 운동 생성 실패")
    void createExerciseFail1() {
        // given
        ExerciseCreateRequest exerciseCreateRequest = RequestFixtures.aExerciseCreateRequest()
            .build();

        request.setAttribute("userId", 0L);

        // when then
        Assertions.assertThatExceptionOfType(BadRequestException.class)
            .isThrownBy(() -> exerciseService.createExercise(exerciseCreateRequest, request))
            .withMessageContaining("User not found");
    }

    @Test
    @DisplayName("루틴이 존재하지 않으면 운동 생성 실패")
    void createExerciseFail2() {
        // given
        UserEntity user = EntityFixtures.aUser()
            .build();
        UserEntity saveUser = usersRepository.save(user);

        ExerciseCreateRequest exerciseCreateRequest = RequestFixtures.aExerciseCreateRequest()
            .build();

        request.setAttribute("userId", saveUser.getId());
        
        // when then
        Assertions.assertThatExceptionOfType(BadRequestException.class)
            .isThrownBy(() -> exerciseService.createExercise(exerciseCreateRequest, request))
            .withMessageContaining("Routine not found");
    }

    @Test
    @DisplayName("운동 생성 성공")
    void createExerciseSuccess() {
        // given
        UserEntity user = EntityFixtures.aUser()
            .build();
        UserEntity saveUser = usersRepository.save(user);

        RoutineCreateRequest routineCreateRequest = RequestFixtures.aRoutineCreateRequest()
            .build();
        RoutineEntity routine = routineRepository.save(RoutineEntity.create(routineCreateRequest, saveUser));

        ExerciseCreateRequest exerciseCreateRequest = RequestFixtures.aExerciseCreateRequest()
            .routineId(routine.getId())
            .build();

        request.setAttribute("userId", saveUser.getId());

        // when
        ExerciseCreateResponse exerciseCreateResponse = exerciseService.createExercise(exerciseCreateRequest, request);

        // then
        Assertions.assertThat(exerciseCreateResponse).isNotNull();
        Assertions.assertThat(exerciseCreateResponse.getExerciseId()).isNotNull();
    }

    @Test
    @DisplayName("운동 id 값이 존재하지 않으면 운동 조회 실패")
    void getExerciseFail1() {
        // given
        final Long exerciseId = 1L;

        // when then
        Assertions.assertThatExceptionOfType(BadRequestException.class)
            .isThrownBy(() -> exerciseService.getExercise(exerciseId))
            .withMessageContaining("Exercise not found");
    }
    
    @Test
    @DisplayName("운동 조회 성공")
    void getExerciseSuccess() {
        // given
        UserEntity user = EntityFixtures.aUser()
            .build();
        UserEntity saveUser = usersRepository.save(user);

        RoutineCreateRequest routineCreateRequest = RequestFixtures.aRoutineCreateRequest()
            .build();
        RoutineEntity routine = routineRepository.save(RoutineEntity.create(routineCreateRequest, saveUser));
        
        ExerciseCreateRequest exerciseCreateRequest = RequestFixtures.aExerciseCreateRequest()
            .routineId(routine.getId())
            .build();
        ExerciseEntity exercise = exerciseRepository.save(ExerciseEntity.create(exerciseCreateRequest, routine, saveUser));

        // when
        ExerciseResponse exerciseResponse = exerciseService.getExercise(exercise.getId());

        // then
        Assertions.assertThat(exerciseResponse).isNotNull();
        Assertions.assertThat(exerciseResponse.getExerciseId()).isEqualTo(exercise.getId());
    }

    @Test
    @DisplayName("운동 목록 조회 성공")
    void getExerciseListSuccess() {
        // given
        UserEntity user = EntityFixtures.aUser()
            .build();
        UserEntity saveUser = usersRepository.save(user);

        RoutineCreateRequest routineCreateRequest = RequestFixtures.aRoutineCreateRequest()
            .build();
        RoutineEntity routine = routineRepository.save(RoutineEntity.create(routineCreateRequest, saveUser));
        
        ExerciseCreateRequest exerciseCreateRequest = RequestFixtures.aExerciseCreateRequest()
            .routineId(routine.getId())
            .build();
        ExerciseEntity exercise = exerciseRepository.save(ExerciseEntity.create(exerciseCreateRequest, routine, saveUser));

        // when
        ExerciseListResponse exerciseListResponse = exerciseService.getExerciseList(routine.getId(), 0, 10);

        // then
        Assertions.assertThat(exerciseListResponse).isNotNull();
        Assertions.assertThat(exerciseListResponse.getExerciseList()).isNotEmpty();
    }

    @Test
    @DisplayName("운동 수정 성공")
    void updateExerciseSuccess() {
        // given
        UserEntity user = EntityFixtures.aUser()
            .build();
        UserEntity saveUser = usersRepository.save(user);

        RoutineCreateRequest routineCreateRequest = RequestFixtures.aRoutineCreateRequest()
            .build();
        RoutineEntity routine = routineRepository.save(RoutineEntity.create(routineCreateRequest, saveUser));
        
        ExerciseCreateRequest exerciseCreateRequest = RequestFixtures.aExerciseCreateRequest()
            .routineId(routine.getId())
            .build();
        ExerciseEntity exercise = exerciseRepository.save(ExerciseEntity.create(exerciseCreateRequest, routine, saveUser));

        // when
        ExerciseUpdateRequest exerciseUpdateRequest = RequestFixtures.aExerciseUpdateRequest()
            .exerciseId(exercise.getId())
            .build();

        request.setAttribute("userId", saveUser.getId());

        // when
        ExerciseResponse exerciseResponse = exerciseService.updateExercise(exerciseUpdateRequest, request);

        // then
        Assertions.assertThat(exerciseResponse).isNotNull();
        Assertions.assertThat(exerciseResponse.getExerciseId()).isEqualTo(exercise.getId());
    }

    @Test
    @DisplayName("운동 삭제 성공")
    void deleteExerciseSuccess() {
        // given
        UserEntity user = EntityFixtures.aUser()
            .build();
        UserEntity saveUser = usersRepository.save(user);

        RoutineCreateRequest routineCreateRequest = RequestFixtures.aRoutineCreateRequest()
            .build();
        RoutineEntity routine = routineRepository.save(RoutineEntity.create(routineCreateRequest, saveUser));

        ExerciseCreateRequest exerciseCreateRequest = RequestFixtures.aExerciseCreateRequest()
            .routineId(routine.getId())
            .build();
        ExerciseEntity exercise = exerciseRepository.save(ExerciseEntity.create(exerciseCreateRequest, routine, saveUser));

        // when
        exerciseService.deleteExercise(exercise.getId());

        // then
        ExerciseEntity findExercise = exerciseRepository.findById(exercise.getId()).get();
        Assertions.assertThat(findExercise.getStatus()).isEqualTo(ExerciseStatus.INACTIVE);
        
    }
}
