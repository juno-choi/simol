# 🔴 project 실행

## 🟠 project 환경변수 설정

프로젝트 폴더 내 `.env` 파일을 생성하여 아래 환경 변수를 설정해주세요.

기본 port 값 변경시 docker-compose.yml 파일에서 수정해서 사용해주세요~!

### 🟢 oun-user 환경 변수 설정

`기본 port` 8080

```
CONTAINER_NAME=oun-auth
SERVER_PROFILE=dev
GOOGLE_CLIENT_ID=your_google_client_id
GOOGLE_CLIENT_SECRET=your_google_client_secret
JWT_SECRET_KEY=your_jwt_secret_key
```

### 🟢 oun-api 환경 변수 설정

`기본 port` 8081

```
CONTAINER_NAME=oun-api
SERVER_PROFILE=dev
JWT_SECRET_KEY=your_jwt_secret_key
```

## 🟠 project 실행

모든 실행 전

```bash
./gradlew clean build
```
실행 후 진행해주세요~!

### 🟢 oun-user

`dev`

```bash
docker-compose -f ./oun-user/docker-compose.yml up -d --build
```

`local 개발시`

```bash
docker-compose -f ./redis/docker-compose.yml up -d --build
```

로컬에서 개발툴로 실행시 redis 서버를 실행하여 진행해주세요.

### 🟢 oun-api

```bash
docker-compose -f ./oun-api/docker-compose.yml up -d --build
```

# 🔴 project 구성

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
        varchar description "세트에 대한 설명"
        int sort "순서"
        enum status "health detail 상태 (ACTIVE, INACTIVE)"
    }

    HealthSetTarget {
        bigint health_set_target_id PK "자동 증가"
        bigint health_set_id FK "health set id"
        int number "목표 횟수"
        int weight "목표 무게"
        int distance "목표 거리"
        int time "목표 수행 시간"
        int spped "목표 속도"
    }

    HealthSetReal {
        bigint health_set_real_id PK "자동 증가"
        bigint health_set_id FK "health set id"
        int number "실제 횟수"
        int weight "실제 무게"
        int distance "실제 거리"
        int time "실제 수행 시간"
        int spped "실제 속도"
    }
    
    Users ||--|{ Routine : "1 N"
    Routine ||--|{ Health : "1 N"
    Health ||--|{ HealthSet : "1 N"
    HealthSet ||--|| HealthSetTarget : "1 1"
    HealthSet ||--|| HealthSetReal : "1 1"
```

# 📗 Reference

[참고자료](./global/reference/README.md)