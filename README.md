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
MYSQL_USER=your_db_user
MYSQL_PASSWORD=your_db_user_password
MYSQL_ROOT_PASSWORD=your_db_root_password
```

### 🟢 oun-api 환경 변수 설정

`기본 port` 8081

```
CONTAINER_NAME=oun-api
SERVER_PROFILE=dev
JWT_SECRET_KEY=your_jwt_secret_key
MYSQL_USER=your_db_user
MYSQL_PASSWORD=your_db_user_password
MYSQL_ROOT_PASSWORD=your_db_root_password
```

### 🟢 mysql 환경 변수 설정

```
MYSQL_USER=your_db_user
MYSQL_PASSWORD=your_db_user_password
MYSQL_ROOT_PASSWORD=your_db_root_password
```

### 🟢 traefik 환경 변수 설정

```
CONTAINER_NAME=oun-auth
SERVER_PROFILE=dev
GOOGLE_CLIENT_ID=your_google_client_id
GOOGLE_CLIENT_SECRET=your_google_client_secret
JWT_SECRET_KEY=your_jwt_secret_key
MYSQL_USER=your_db_user
MYSQL_PASSWORD=your_db_user_password
MYSQL_ROOT_PASSWORD=your_db_root_password
```

## 🟠 project 실행

### 🟢 각 프로젝트 별로 실행

모든 실행 전

```bash
./gradlew clean build
```
실행 후 진행해주세요~!

1. redis
2. mysql
3. oun-user
4. oun-api

순서로 실행해주시면 됩니다.


```
docker-compose -f ./redis/docker-compose.yml up -d --build
docker-compose -f ./mysql/docker-compose.yml up -d --build
docker-compose -f ./oun-user/docker-compose.yml up -d --build
docker-compose -f ./oun-api/docker-compose.yml up -d --build
```

### 🟢 traefik 실행

1. redis
2. mysql
3. traefik

순서로 실행해주시면 됩니다.



```
docker-compose -f ./redis/docker-compose.yml up -d --build
docker-compose -f ./mysql/docker-compose.yml up -d --build
docker-compose -f ./traefik/docker-compose.yml up -d --build
```

---

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
        enum health_set_type "운동 타입"
        varchar description "세트에 대한 설명"
        int set_number "세트 순서"
        int set_count "세트당 개수"
        int set_weight "세트 무게"
        int set_distance "세트 거리"
        int set_time "세트 시간"
        int set_speed "세트 스피드"
        enum status "health detail 상태 (ACTIVE, INACTIVE)"
    }
    
    Users ||--|{ Routine : "1 N"
    Routine ||--|{ Health : "1 N"
    Health ||--|{ HealthSet : "1 N"
```

# 📗 Reference

[참고자료](./global/reference/README.md)