package com.simol.ouncommon.routine.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.simol.ouncommon.auth.entity.UserEntity;
import com.simol.ouncommon.global.entity.GlobalEntity;
import com.simol.ouncommon.health.entity.HealthEntity;
import com.simol.ouncommon.routine.dto.RoutineCreateRequest;
import com.simol.ouncommon.routine.dto.RoutineHealthUpdateRequest;
import com.simol.ouncommon.routine.dto.RoutineUpdateRequest;
import com.simol.ouncommon.routine.enums.RoutineStatus;

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
    private List<HealthEntity> healthList = new ArrayList<>();

    @Column(nullable = false)
    private String name;    //루틴 이름

    @Column(nullable = true)
    private String description; //루틴 설명

    @Enumerated(EnumType.STRING)
    private RoutineStatus status;   //루틴 상태

    private int sort;

    @Builder
    protected RoutineEntity(String name, String description, int sort, RoutineStatus status, List<HealthEntity> healthList, UserEntity user) {
        this.name = name;
        this.description = description;
        this.sort = sort;
        this.status = status;
        this.user = user;
        this.healthList = healthList;
    }

    public void update(RoutineUpdateRequest routineUpdateRequest) {
        this.name = routineUpdateRequest.getName();
        this.description = routineUpdateRequest.getDescription();
        this.status = routineUpdateRequest.getStatus();
    }

    public void addHealth(HealthEntity health) {
        healthList.add(health);
        health.updateRoutine(this);
    }

    public void updateHealthList(List<RoutineHealthUpdateRequest> healthRequests) {
        // 기존 Health ID를 Map으로 관리
        Map<Long, HealthEntity> existingHealthMap = this.healthList.stream()
            .filter(health -> health.getId() != null)
            .collect(Collectors.toMap(HealthEntity::getId, health -> health));
        
        List<HealthEntity> updatedHealthList = new ArrayList<>();
        
        for (RoutineHealthUpdateRequest healthRequest : healthRequests) {
            if (healthRequest.getHealthId() != null && existingHealthMap.containsKey(healthRequest.getHealthId())) {
                // 기존 Health 업데이트
                HealthEntity existingHealth = existingHealthMap.get(healthRequest.getHealthId());
                existingHealth.update(healthRequest);
                updatedHealthList.add(existingHealth);
                existingHealthMap.remove(healthRequest.getHealthId());
            } else {
                // 새 Health 생성
                HealthEntity newHealth = HealthEntity.create(healthRequest, this);
                updatedHealthList.add(newHealth);
            }
        }
        
        // 컬렉션 교체
        this.healthList.clear();
        this.healthList.addAll(updatedHealthList);
    }

    public static RoutineEntity create(RoutineCreateRequest routineCreateRequest, UserEntity user) {
        return RoutineEntity.builder()
            .name(routineCreateRequest.getName())
            .description(routineCreateRequest.getDescription())
            .sort(routineCreateRequest.getSort())
            .status(RoutineStatus.ACTIVE)
            .user(user)
            .healthList(new ArrayList<>())
            .build();
    }
}   