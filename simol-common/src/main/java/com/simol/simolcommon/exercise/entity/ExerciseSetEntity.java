package com.simol.simolcommon.exercise.entity;

import com.simol.simolcommon.exercise.dto.ExerciseSetCreateRequest;
import com.simol.simolcommon.exercise.dto.ExerciseSetListUpdateRequest;
import com.simol.simolcommon.exercise.dto.ExerciseSetUpdateRequest;
import com.simol.simolcommon.exercise.enums.ExerciseSetStatus;
import com.simol.simolcommon.global.entity.GlobalEntity;

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
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "EXERCISE_SET")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class ExerciseSetEntity extends GlobalEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exercise_set_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exercise_id")
    private ExerciseEntity exercise;

    // 세트 번호
    private int number;
    // 세트당 개수
    private int count;
    // 세트 무게
    private int weight;
    // 세트 거리
    private int distance;
    // 세트 시간
    private int time;
    // 세트 속도
    private double speed;

    private String description;
    
    @Enumerated(EnumType.STRING)
    private ExerciseSetStatus status;

    public static ExerciseSetEntity create(ExerciseSetCreateRequest exerciseDetailCreateRequest, ExerciseEntity exercise) {
        return ExerciseSetEntity.builder()
            .exercise(exercise)
            .number(exerciseDetailCreateRequest.getNumber())
            .count(exerciseDetailCreateRequest.getCount())
            .weight(exerciseDetailCreateRequest.getWeight())
            .distance(exerciseDetailCreateRequest.getDistance())
            .time(exerciseDetailCreateRequest.getTime())
            .speed(exerciseDetailCreateRequest.getSpeed())
            .description(exerciseDetailCreateRequest.getDescription())
            .status(ExerciseSetStatus.ACTIVE)
            .build();
    }

    public void updateExercise(ExerciseEntity exerciseEntity) {
        this.exercise = exerciseEntity;
    }
    
    public void update(ExerciseSetListUpdateRequest exerciseSetUpdateRequest) {
        this.number = exerciseSetUpdateRequest.getNumber();
        this.count = exerciseSetUpdateRequest.getCount();
        this.weight = exerciseSetUpdateRequest.getWeight();
        this.distance = exerciseSetUpdateRequest.getDistance();
        this.time = exerciseSetUpdateRequest.getTime();
        this.speed = exerciseSetUpdateRequest.getSpeed();
        this.description = exerciseSetUpdateRequest.getDescription();
    }

    public static ExerciseSetEntity create(ExerciseSetListUpdateRequest exerciseSetUpdateRequest, ExerciseEntity exercise) {
        return ExerciseSetEntity.builder()
            .exercise(exercise)
            .number(exerciseSetUpdateRequest.getNumber())
            .count(exerciseSetUpdateRequest.getCount())
            .weight(exerciseSetUpdateRequest.getWeight())
            .distance(exerciseSetUpdateRequest.getDistance())
            .time(exerciseSetUpdateRequest.getTime())
            .speed(exerciseSetUpdateRequest.getSpeed())
            .description(exerciseSetUpdateRequest.getDescription())
            .status(ExerciseSetStatus.ACTIVE)
            .build();
    }

    public void update(ExerciseSetUpdateRequest exerciseSetUpdateRequest) {
        this.number = exerciseSetUpdateRequest.getNumber();
        this.count = exerciseSetUpdateRequest.getCount();
        this.weight = exerciseSetUpdateRequest.getWeight();
        this.distance = exerciseSetUpdateRequest.getDistance();
        this.time = exerciseSetUpdateRequest.getTime();
        this.speed = exerciseSetUpdateRequest.getSpeed();
        this.description = exerciseSetUpdateRequest.getDescription();
    }

}