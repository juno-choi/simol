package com.simol.simolcommon.routine.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.simol.simolcommon.auth.entity.UserEntity;
import com.simol.simolcommon.exercise.entity.ExerciseEntity;
import com.simol.simolcommon.global.entity.GlobalEntity;
import com.simol.simolcommon.routine.dto.RoutineCreateRequest;
import com.simol.simolcommon.routine.dto.RoutineExerciseUpdateRequest;
import com.simol.simolcommon.routine.dto.RoutineUpdateRequest;
import com.simol.simolcommon.routine.enums.RoutineStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "routine")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RoutineEntity extends GlobalEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "routine_id")
    private Long id;

    // user와 연관관계 맺기
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @OneToMany(mappedBy = "routine", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ExerciseEntity> exerciseList = new ArrayList<>();

    @Column(nullable = false)
    private String name;    //루틴 이름

    @Column(nullable = true)
    private String description; //루틴 설명

    @Enumerated(EnumType.STRING)
    private RoutineStatus status;   //루틴 상태


    @Builder
    protected RoutineEntity(String name, String description, RoutineStatus status, List<ExerciseEntity> exerciseList, UserEntity user) {
        this.name = name;
        this.description = description;
        this.status = status;
        this.user = user;
        this.exerciseList = exerciseList;
    }

    public void update(RoutineUpdateRequest routineUpdateRequest) {
        this.name = routineUpdateRequest.getName();
        this.description = routineUpdateRequest.getDescription();
        this.status = routineUpdateRequest.getStatus();
    }

    public void addExercise(ExerciseEntity exercise) {
        exerciseList.add(exercise);
        exercise.updateRoutine(this);
    }

    public void updateExerciseList(List<RoutineExerciseUpdateRequest> routineExerciseUpdateRequestList) {
        // 기존 Health ID를 Map으로 관리
        Map<Long, ExerciseEntity> existingExerciseMap = this.exerciseList.stream()
            .filter(exercise -> exercise.getId() != null)
            .collect(Collectors.toMap(ExerciseEntity::getId, exercise -> exercise));
        
        List<ExerciseEntity> updatedExerciseList = new ArrayList<>();
        
        for (RoutineExerciseUpdateRequest routineExerciseUpdateRequest : routineExerciseUpdateRequestList) {
            if (routineExerciseUpdateRequest.getExerciseId() != null && existingExerciseMap.containsKey(routineExerciseUpdateRequest.getExerciseId())) {
                // 기존 Health 업데이트
                ExerciseEntity existingExercise = existingExerciseMap.get(routineExerciseUpdateRequest.getExerciseId());
                existingExercise.update(routineExerciseUpdateRequest);
                updatedExerciseList.add(existingExercise);
                existingExerciseMap.remove(routineExerciseUpdateRequest.getExerciseId());
            } else {
                // 새 Health 생성
                ExerciseEntity newExercise = ExerciseEntity.create(routineExerciseUpdateRequest, this);
                updatedExerciseList.add(newExercise);
            }
        }
        
        // 컬렉션 교체
        this.exerciseList.clear();
        this.exerciseList.addAll(updatedExerciseList);
    }

    public static RoutineEntity create(RoutineCreateRequest routineCreateRequest, UserEntity user) {
        return RoutineEntity.builder()
            .name(routineCreateRequest.getName())
            .description(routineCreateRequest.getDescription())
            .status(RoutineStatus.ACTIVE)
            .user(user)
            .exerciseList(new ArrayList<>())
            .build();
    }

    public void delete() {
        this.status = RoutineStatus.INACTIVE;
    }
}   