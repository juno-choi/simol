package com.simol.ounapi.exercise.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.simol.ounapi.fixture.EntityFixtures;
import com.simol.ounapi.fixture.RequestFixtures;
import com.simol.simolcommon.common.auth.entity.UserEntity;
import com.simol.simolcommon.common.auth.repository.UsersRepository;
import com.simol.simolcommon.common.exception.BadRequestException;
import com.simol.simolcommon.oun.exercise.dto.ExerciseCreateRequest;
import com.simol.simolcommon.oun.exercise.dto.ExerciseSetCreateRequest;
import com.simol.simolcommon.oun.exercise.entity.ExerciseEntity;
import com.simol.simolcommon.oun.exercise.entity.ExerciseSetEntity;
import com.simol.simolcommon.oun.exercise.repository.ExerciseRepository;
import com.simol.simolcommon.oun.exercise.repository.ExerciseSetRepository;
import com.simol.simolcommon.oun.exercise.vo.ExerciseSetCreateResponse;
import com.simol.simolcommon.oun.exercise.vo.ExerciseSetListResponse;
import com.simol.simolcommon.oun.exercise.vo.ExerciseSetResponse;
import com.simol.simolcommon.oun.routine.dto.RoutineCreateRequest;
import com.simol.simolcommon.oun.routine.entity.RoutineEntity;
import com.simol.simolcommon.oun.routine.repository.RoutineRepository;

@SpringBootTest
public class ExerciseSetServiceImplTest {
    @Autowired
    private ExerciseSetServiceImpl exerciseSetService;

    @Autowired
    private ExerciseSetRepository exerciseSetRepository;

    @Autowired
    private ExerciseRepository exerciseRepository;

    @Autowired
    private RoutineRepository routineRepository;

    @Autowired
    private UsersRepository usersRepository;

    @AfterEach
    void cleanUp() {
        exerciseSetRepository.deleteAll();
        exerciseRepository.deleteAll();
        routineRepository.deleteAll();
        usersRepository.deleteAll();
    }
    
    @Test
    @DisplayName("운동 세트 생성 실패")
    void createExerciseSetFail() {
        // given
        ExerciseSetCreateRequest exerciseSetCreateRequest = RequestFixtures.aExerciseSetCreateRequest()
            .build();

        // when then
        Assertions.assertThatExceptionOfType(BadRequestException.class)
            .isThrownBy(() -> exerciseSetService.createExerciseSet(exerciseSetCreateRequest))
            .withMessageContaining("Exercise not found");
    }

    @Test
    @DisplayName("운동 세트 생성 성공")
    void createExerciseSetSuccess() {
        // given
        UserEntity user = EntityFixtures.aUser()
            .build();
        UserEntity saveUser = usersRepository.save(user);

        RoutineCreateRequest routineCreateRequest = RequestFixtures.aRoutineCreateRequest()
            .build();
        RoutineEntity routine = routineRepository.save(RoutineEntity.create(routineCreateRequest, saveUser));

        ExerciseCreateRequest exerciseCreateRequest = RequestFixtures.aExerciseCreateRequest()
            .build();
        ExerciseEntity exercise = exerciseRepository.save(ExerciseEntity.create(exerciseCreateRequest, routine, saveUser));

        ExerciseSetCreateRequest exerciseSetCreateRequest = RequestFixtures.aExerciseSetCreateRequest()
            .exerciseId(exercise.getId())
            .build();

        // when
        ExerciseSetCreateResponse exerciseSetCreateResponse = exerciseSetService.createExerciseSet(exerciseSetCreateRequest);

        // then
        Assertions.assertThat(exerciseSetCreateResponse).isNotNull();
    }

    @Test
    @DisplayName("운동 세트 조회 실패")
    void getExerciseSetFail() {
        // given
        Long exerciseSetId = 1L;

        // when then
        Assertions.assertThatExceptionOfType(BadRequestException.class)
            .isThrownBy(() -> exerciseSetService.getExerciseSet(exerciseSetId))
            .withMessageContaining("ExerciseSet not found");
    }

    @Test
    @DisplayName("운동 세트 조회 성공")
    void getExerciseSetSuccess() {
        // given
        Long exerciseSetId = 1L;
        UserEntity user = EntityFixtures.aUser()
            .build();
        UserEntity saveUser = usersRepository.save(user);

        RoutineCreateRequest routineCreateRequest = RequestFixtures.aRoutineCreateRequest()
            .build();
        RoutineEntity routine = routineRepository.save(RoutineEntity.create(routineCreateRequest, saveUser));

        ExerciseCreateRequest exerciseCreateRequest = RequestFixtures.aExerciseCreateRequest()
            .build();
        ExerciseEntity exercise = exerciseRepository.save(ExerciseEntity.create(exerciseCreateRequest, routine, saveUser));

        ExerciseSetCreateRequest exerciseSetCreateRequest = RequestFixtures.aExerciseSetCreateRequest()
            .build();
        ExerciseSetEntity exerciseSet = exerciseSetRepository.save(ExerciseSetEntity.create(exerciseSetCreateRequest, exercise));

        // when
        ExerciseSetResponse exerciseSetResponse = exerciseSetService.getExerciseSet(exerciseSet.getId());

        // then
        Assertions.assertThat(exerciseSetResponse).isNotNull();
    }

    @Test
    @DisplayName("운동 세트 목록 조회 성공")
    void getExerciseSetListSuccess() {
        // given
        Long exerciseId = 1L;
        UserEntity user = EntityFixtures.aUser()
            .build();
        UserEntity saveUser = usersRepository.save(user);

        RoutineCreateRequest routineCreateRequest = RequestFixtures.aRoutineCreateRequest()
            .build();
        RoutineEntity routine = routineRepository.save(RoutineEntity.create(routineCreateRequest, saveUser));

        ExerciseCreateRequest exerciseCreateRequest = RequestFixtures.aExerciseCreateRequest()
            .build();
        ExerciseEntity exercise = exerciseRepository.save(ExerciseEntity.create(exerciseCreateRequest, routine, saveUser));

        ExerciseSetCreateRequest exerciseSetCreateRequest = RequestFixtures.aExerciseSetCreateRequest()
            .build();
        ExerciseSetEntity exerciseSet = exerciseSetRepository.save(ExerciseSetEntity.create(exerciseSetCreateRequest, exercise));

        // when
        ExerciseSetListResponse exerciseSetListResponse = exerciseSetService.getExerciseSetList(0, 10, exercise.getId());

        // then
        Assertions.assertThat(exerciseSetListResponse).isNotNull();
    }
}
