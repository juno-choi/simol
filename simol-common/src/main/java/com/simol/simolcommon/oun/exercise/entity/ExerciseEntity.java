package com.simol.simolcommon.oun.exercise.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.simol.simolcommon.auth.entity.UserEntity;
import com.simol.simolcommon.global.entity.GlobalEntity;
import com.simol.simolcommon.oun.exercise.dto.ExerciseCreateRequest;
import com.simol.simolcommon.oun.exercise.dto.ExerciseSetListUpdateRequest;
import com.simol.simolcommon.oun.exercise.dto.ExerciseUpdateRequest;
import com.simol.simolcommon.oun.exercise.enums.ExerciseStatus;
import com.simol.simolcommon.oun.exercise.enums.ExerciseType;
import com.simol.simolcommon.oun.routine.dto.RoutineExerciseUpdateRequest;
import com.simol.simolcommon.oun.routine.entity.RoutineEntity;

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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "EXERCISE")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class ExerciseEntity extends GlobalEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exercise_id")
    private Long id;

    // rotine 과 연관관계 매핑
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "routine_id")
    private RoutineEntity routine;

    // health 상태
    @Enumerated(EnumType.STRING)
    private ExerciseStatus status;

    @Enumerated(EnumType.STRING)
    private ExerciseType type;

    // health 세트 리스트
    @OneToMany(mappedBy = "exercise", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ExerciseSetEntity> exerciseSetList;

    // health 이름
    private String name;

    // health 설명
    private String description;

    // health 순서
    private int sort;

    public static ExerciseEntity create(ExerciseCreateRequest healthCreateRequest, RoutineEntity routine, UserEntity user) {
        return ExerciseEntity.builder()
                .routine(routine)
                .name(healthCreateRequest.getName())
                .description(healthCreateRequest.getDescription())
                .sort(healthCreateRequest.getSort())
                .status(ExerciseStatus.ACTIVE)
                .type(healthCreateRequest.getType())
                .build();
    }

    public void update(ExerciseUpdateRequest healthUpdateRequest) {
        this.name = healthUpdateRequest.getName();
        this.description = healthUpdateRequest.getDescription();
        this.sort = healthUpdateRequest.getSort();
        this.status = healthUpdateRequest.getStatus();
    }

    public void updateRoutine(RoutineEntity routine) {
        this.routine = routine;
    }

    public static ExerciseEntity create(RoutineExerciseUpdateRequest healthUpdateRequest, RoutineEntity routine) {
        return ExerciseEntity.builder()
                .name(healthUpdateRequest.getName())
                .description(healthUpdateRequest.getDescription())
                .sort(healthUpdateRequest.getSort())
                .status(healthUpdateRequest.getStatus())
                .type(healthUpdateRequest.getType())
                .routine(routine)
                .build();
    }

    public void update(RoutineExerciseUpdateRequest healthRequest) {
        this.name = healthRequest.getName();
        this.description = healthRequest.getDescription();
        this.sort = healthRequest.getSort();
        this.status = healthRequest.getStatus();
        this.type = healthRequest.getType();
    }

    public void updateExerciseSetList(List<ExerciseSetListUpdateRequest> exerciseSetList) {
                // 기존 Health ID를 Map으로 관리
                Map<Long, ExerciseSetEntity> existingHealthMap = this.exerciseSetList.stream()
                .filter(health -> health.getId() != null)
                .collect(Collectors.toMap(ExerciseSetEntity::getId, healthSet -> healthSet));
            
            List<ExerciseSetEntity> updatedHealthSetList = new ArrayList<>();
            
            for (ExerciseSetListUpdateRequest exerciseSetRequest : exerciseSetList) {
                if (exerciseSetRequest.getExerciseSetId() != null && existingHealthMap.containsKey(exerciseSetRequest.getExerciseSetId())) {
                    // 기존 Health 업데이트
                    ExerciseSetEntity existingHealthSet = existingHealthMap.get(exerciseSetRequest.getExerciseSetId());
                    existingHealthSet.update(exerciseSetRequest);
                    updatedHealthSetList.add(existingHealthSet);
                    existingHealthMap.remove(exerciseSetRequest.getExerciseSetId());
                } else {
                    // 새 Health 생성
                    ExerciseSetEntity newHealthSet = ExerciseSetEntity.create(exerciseSetRequest, this);
                    updatedHealthSetList.add(newHealthSet);
                }
            }
            
            // 컬렉션 교체
            this.exerciseSetList.clear();
            this.exerciseSetList.addAll(updatedHealthSetList);
    }

    public void addExerciseSet(ExerciseSetEntity exerciseSet) {
        exerciseSetList.add(exerciseSet);
        exerciseSet.updateExercise(this);
    }

    public void delete() {
        this.status = ExerciseStatus.INACTIVE;
    }

}
