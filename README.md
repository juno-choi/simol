# 🔴 project 실행

## 🟠 Reference

[참고자료](./global/reference/README.md)

## 🟠 redis 실행

colima or docker desktop 설치 후
```bash
colima start
```

```bash
docker-compose -f redis/docker-compose.yml up -d
```

## 🟠 oun ERD

```mermaid
---
title: oun
---

erDiagram

    Users {
        bigint user_id PK "자동 증가"
        varchar email UK "이메일"
        varchar profile_image "프로필"
        varchar role "권한"
    }
    
    Routine {
        bigint routine_id PK "자동 증가"
        bigint user_id FK "user ID"
        varchar name "routine 이름 (Not Null)"
        varchar description "루틴 설명"
        enum status "routine 상태 (ACTIVE, INACTIVE)"
    }
    
    Health {
        bigint health_id PK "자동 증가"
        bigint routine_id FK "routine ID"
        varchar name "health 이름"
        int sort "순서"
        enum status "health 상태 (ACTIVE, INACTIVE)"
    }

    HealthSet {
        bigint health_set_id PK "자동 증가"
        bigint health_id FK "health ID"
        int sort "순서"
        enum status "health detail 상태 (ACTIVE, INACTIVE)"
    }

    HealthSetTarget {
        bigint health_set_target_id PK "자동 증가"
        int number "목표 횟수"
        int weight "목표 무게"
        int distance "목표 거리"
        int spped "목표 속도"
    }

    HealthSetReal {
        bigint health_set_real_id PK "자동 증가"
        int number "실제 횟수"
        int weight "실제 무게"
        int distance "실제 거리"
        int spped "실제 속도"
    }
    
    Users ||--|{ Routine : "1 N"
    Routine ||--|{ Health : "1 N"
    Health ||--|{ HealthSet : "1 N"
    HealthSet ||--|| HealthSetTarget : "1 1"
    HealthSet ||--|| HealthSetReal : "1 1"
```