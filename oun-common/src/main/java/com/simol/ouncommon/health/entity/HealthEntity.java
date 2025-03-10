package com.simol.ouncommon.health.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.simol.ouncommon.auth.entity.UserEntity;
import com.simol.ouncommon.global.entity.GlobalEntity;
import com.simol.ouncommon.health.dto.HealthCreateRequest;
import com.simol.ouncommon.health.dto.HealthHealthSetUpdateRequest;
import com.simol.ouncommon.health.dto.HealthUpdateRequest;
import com.simol.ouncommon.health.enums.HealthStatus;
import com.simol.ouncommon.healthset.entity.HealthSetEntity;
import com.simol.ouncommon.routine.dto.RoutineHealthUpdateRequest;
import com.simol.ouncommon.routine.entity.RoutineEntity;

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
@Table(name = "HEALTH")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class HealthEntity extends GlobalEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "health_id")
    private Long id;

    // rotine 과 연관관계 매핑
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "routine_id")
    private RoutineEntity routine;

    // health 상태
    @Enumerated(EnumType.STRING)
    private HealthStatus status;

    // health 세트 리스트
    @OneToMany(mappedBy = "health", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HealthSetEntity> healthSetList;

    // health 이름
    private String name;

    // health 설명
    private String description;

    // health 순서
    private int sort;

    public static HealthEntity create(HealthCreateRequest healthCreateRequest, RoutineEntity routine, UserEntity user) {
        return HealthEntity.builder()
                .routine(routine)
                .name(healthCreateRequest.getName())
                .description(healthCreateRequest.getDescription())
                .sort(healthCreateRequest.getSort())
                .status(HealthStatus.ACTIVE)
                .build();
    }

    public void update(HealthUpdateRequest healthUpdateRequest) {
        this.name = healthUpdateRequest.getName();
        this.description = healthUpdateRequest.getDescription();
        this.sort = healthUpdateRequest.getSort();
        this.status = healthUpdateRequest.getStatus();
    }

    public void updateRoutine(RoutineEntity routine) {
        this.routine = routine;
    }

    public static HealthEntity create(RoutineHealthUpdateRequest healthUpdateRequest, RoutineEntity routine) {
        return HealthEntity.builder()
                .name(healthUpdateRequest.getName())
                .description(healthUpdateRequest.getDescription())
                .sort(healthUpdateRequest.getSort())
                .status(healthUpdateRequest.getStatus())
                .routine(routine)
                .build();
    }

    public void update(RoutineHealthUpdateRequest healthRequest) {
        this.name = healthRequest.getName();
        this.description = healthRequest.getDescription();
        this.sort = healthRequest.getSort();
        this.status = healthRequest.getStatus();
    }

    public void updateHealthSetList(List<HealthHealthSetUpdateRequest> healthSetList) {
                // 기존 Health ID를 Map으로 관리
                Map<Long, HealthSetEntity> existingHealthMap = this.healthSetList.stream()
                .filter(health -> health.getId() != null)
                .collect(Collectors.toMap(HealthSetEntity::getId, healthSet -> healthSet));
            
            List<HealthSetEntity> updatedHealthSetList = new ArrayList<>();
            
            for (HealthHealthSetUpdateRequest healthSetRequest : healthSetList) {
                if (healthSetRequest.getHealthSetId() != null && existingHealthMap.containsKey(healthSetRequest.getHealthSetId())) {
                    // 기존 Health 업데이트
                    HealthSetEntity existingHealthSet = existingHealthMap.get(healthSetRequest.getHealthSetId());
                    existingHealthSet.update(healthSetRequest);
                    updatedHealthSetList.add(existingHealthSet);
                    existingHealthMap.remove(healthSetRequest.getHealthSetId());
                } else {
                    // 새 Health 생성
                    HealthSetEntity newHealthSet = HealthSetEntity.create(healthSetRequest, this);
                    updatedHealthSetList.add(newHealthSet);
                }
            }
            
            // 컬렉션 교체
            this.healthSetList.clear();
            this.healthSetList.addAll(updatedHealthSetList);
    }

    public void addHealthSet(HealthSetEntity healthSet) {
        healthSetList.add(healthSet);
        healthSet.updateHealth(this);
    }

    public void delete() {
        this.status = HealthStatus.INACTIVE;
    }

}
