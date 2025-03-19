
# 🔴 project preview

[프로젝트 미리보기](https://simol-oun.vercel.app/)

# 📗 Reference

[참고자료](./global/reference/README.md)

# 👨‍💻 Proejct 실행

순서대로 실행하시길 권장드립니다.

[mysql 실행방법](./mysql/README.md)

[redis 실행방법](./redis/README.md)

[traefik 실행방법](./traefik/README.md)

[simol-user 실행방법](./simol-user/README.md)

[oun-api 실행방법](./oun-api/README.md)

[monitoring](./monitoring/README.md)

# 🔴 front project

[oun-front github](https://github.com/juno-choi/oun-front)

# 🔴 project 구성

`oun-common` 프로젝트 전체적으로 사용되는 entity, dto, vo, repository 등 공동 사용 모듈

`simol-user` 회원 가입, 로그인 정보를 관리하는 모듈

`oun-api` endpoint를 관리하는 모듈

## 🟠 infra 구성

### 🟢 구성도
```mermaid
---
title: simol 프로젝트 인프라 구성도
---
flowchart TB
    subgraph "클라이언트"
        Client[사용자 브라우저/앱]
    end

    subgraph "API 게이트웨이"
        Gateway[Traefik]
    end

    subgraph "인증"
        User[simol-user/회원 관리 모듈]
    end

    subgraph "서비스"
        API[oun-api/엔드포인트 관리 모듈]
    end

    subgraph "공통 모듈"
        Common[oun-common/공통 모듈]
    end

    subgraph "모니터링 시스템"
        Prometheus[prometheus]
        Grafana[grafana]
    end

    subgraph "데이터베이스"
        DB[(MySql)]
    end
    

    Client --> Gateway
    Gateway --> User
    User --> API
    
    API --> Common
    Common --> DB

    Prometheus --> API
    Prometheus --> Common
    Grafana --> Prometheus

    classDef service stroke:#333,stroke-width:2px
    classDef monitoring fill:#ff0000,stroke:#333,stroke-width:2px
    classDef database fill:#bbf,stroke:#333,stroke-width:2px
    classDef client fill:#98fb98,stroke:#333,stroke-width:2px
    classDef gateway fill:#00ffff,stroke:#333,stroke-width:2px
    
    class Client client
    class Gateway gateway
    class API,User,Common service
    class Prometheus,Grafana monitoring
    class DB database
```

### 🟢 flow chart

```mermaid
flowchart TD
    Client[클라이언트] --> |HTTP 요청| Gateway[API Gateway]
    Gateway --> |인증 헤더 추가| AuthFilter[인증 필터]
    
    subgraph "인증 처리"
        AuthFilter --> |토큰 검증| ApiJwtTokenProvider[JWT 토큰 제공자]
        ApiJwtTokenProvider --> |사용자 정보 조회| UsersRepository[사용자 저장소]
        ApiJwtTokenProvider --> |인증 객체 생성| SecurityContext[보안 컨텍스트]
    end
    
    AuthFilter --> |인증 성공| Controllers[컨트롤러 계층]
    AuthFilter --> |인증 실패| AuthError[인증 오류 처리]
    AuthError --> |401 응답| Client
    
    Controllers --> |요청 처리| Services[서비스 계층]
    Services --> |데이터 접근| Repositories[저장소 계층]
    Repositories --> |엔티티 조회/저장| Database[(데이터베이스)]
    
    Services --> |응답 생성| Controllers
    Controllers --> |HTTP 응답| Client
    
    subgraph "예외 처리"
        Controllers --> |예외 발생| ExceptionHandler[전역 예외 처리기]
        ExceptionHandler --> |오류 응답| Client
    end
    
    classDef client stroke:#333,stroke-width:2px
    classDef auth stroke:#333,stroke-width:2px
    classDef controller stroke:#333,stroke-width:2px
    classDef service stroke:#333,stroke-width:2px
    classDef repository stroke:#333,stroke-width:2px
    classDef database stroke:#333,stroke-width:2px
    classDef error stroke:#f00,stroke-width:2px
    
    class Client client
    class Gateway,AuthFilter,ApiJwtTokenProvider,SecurityContext auth
    class Controllers controller
    class Services service
    class Repositories,UsersRepository repository
    class Database database
    class AuthError,ExceptionHandler error
```

## 🟠 시스템 구성

### 🟢 인증

```mermaid
sequenceDiagram
    actor User as 사용자
    participant Client as 클라이언트
    participant Gateway as API 게이트웨이
    participant AuthFilter as 인증 필터
    participant JwtProvider as JWT 토큰 제공자
    participant UserRepo as 사용자 저장소
    participant DB as 데이터베이스
    participant Security as 보안 컨텍스트
    
    User->>Client: API 요청
    Client->>Gateway: HTTP 요청
    Gateway->>AuthFilter: 요청 전달
    
    AuthFilter->>AuthFilter: 화이트리스트 URL 확인
    
    alt 화이트리스트 URL
        AuthFilter->>Gateway: 인증 없이 통과
    else 인증 필요
        AuthFilter->>JwtProvider: 인증 요청
        
        alt 테스트 요청 (actuator)
            JwtProvider->>JwtProvider: 테스트 인증 생성
            JwtProvider->>Security: 테스트 인증 설정
        else 일반 요청
            JwtProvider->>JwtProvider: X-User-Id, X-User-Role 헤더 확인
            
            alt 테스트 사용자
                JwtProvider->>UserRepo: 테스트 사용자 조회/생성
                UserRepo->>DB: 테스트 사용자 조회
                DB->>UserRepo: 사용자 정보
                UserRepo->>JwtProvider: 테스트 사용자 정보
                JwtProvider->>Security: 테스트 인증 설정
            else 일반 사용자
                JwtProvider->>Security: 사용자 인증 설정
            end
        end
        
        JwtProvider->>AuthFilter: 인증 결과
        
        alt 인증 실패
            AuthFilter->>Client: 401 Unauthorized
            Client->>User: 인증 오류 표시
        else 인증 성공
            AuthFilter->>AuthFilter: 사용자 정보 요청 속성에 추가
            AuthFilter->>Gateway: 인증된 요청 전달
        end
    end
```

### 🟢 루틴 생성

```mermaid
sequenceDiagram
    actor User as 사용자
    participant Client as 클라이언트
    participant Controller as 루틴 컨트롤러
    participant Service as 루틴 서비스
    participant Repository as 저장소
    
    %% 루틴 생성
    User->>Client: 루틴 생성 요청
    Client->>Controller: POST /api/routine
    Controller->>Service: createRoutine(request)
    Service->>Repository: 사용자 조회
    Repository->>Service: 사용자 정보
    Service->>Repository: 루틴 저장
    Repository->>Service: 저장된 루틴
    Service->>Controller: 응답 생성
    Controller->>Client: 201 Created
    Client->>User: 결과 표시
    
    %% 루틴 조회
    User->>Client: 루틴 조회 요청
    Client->>Controller: GET /api/routine/{id}
    Controller->>Service: getRoutine(id)
    Service->>Repository: 루틴 조회
    Repository->>Service: 루틴 정보
    Service->>Controller: 응답 생성
    Controller->>Client: 200 OK
    Client->>User: 결과 표시
    
    %% 루틴 수정
    User->>Client: 루틴 수정 요청
    Client->>Controller: PUT /api/routine
    Controller->>Service: updateRoutine(request)
    Service->>Repository: 루틴 조회
    Repository->>Service: 루틴 정보
    Service->>Repository: 루틴 업데이트
    Repository->>Service: 업데이트된 루틴
    Service->>Controller: 응답 생성
    Controller->>Client: 200 OK
    Client->>User: 결과 표시
```

### 🟢 운동 프로세스

```mermaid
sequenceDiagram
    actor User as 사용자
    participant Client as 클라이언트
    participant Controller as 운동 컨트롤러
    participant Service as 운동 서비스
    participant Repository as 저장소
    
    %% 운동 생성
    User->>Client: 운동 생성 요청
    Client->>Controller: POST /api/routine/exercise
    Controller->>Service: createExercise(request)
    Service->>Repository: 사용자 및 루틴 조회
    Repository->>Service: 사용자 및 루틴 정보
    Service->>Repository: 운동 저장
    Repository->>Service: 저장된 운동
    Service->>Controller: 응답 생성
    Controller->>Client: 201 Created
    Client->>User: 결과 표시
    
    %% 운동 세트 생성
    User->>Client: 운동 세트 생성 요청
    Client->>Controller: POST /api/routine/exercise/set
    Controller->>Service: createExerciseSet(request)
    Service->>Repository: 운동 조회
    Repository->>Service: 운동 정보
    Service->>Repository: 운동 세트 저장
    Repository->>Service: 저장된 운동 세트
    Service->>Controller: 응답 생성
    Controller->>Client: 201 Created
    Client->>User: 결과 표시
    
    %% 운동 수정
    User->>Client: 운동 수정 요청
    Client->>Controller: PUT /api/routine/exercise
    Controller->>Service: updateExercise(request)
    Service->>Repository: 운동 조회
    Repository->>Service: 운동 정보
    Service->>Repository: 운동 및 세트 업데이트
    Repository->>Service: 업데이트된 운동
    Service->>Controller: 응답 생성
    Controller->>Client: 200 OK
    Client->>User: 결과 표시
```

### 🟢 운동 세트

```mermaid
sequenceDiagram
    actor User as 사용자
    participant Client as 클라이언트
    participant Controller as 운동 세트 컨트롤러
    participant Service as 운동 세트 서비스
    participant Repository as 저장소
    
    %% 운동 세트 생성
    User->>Client: 운동 세트 생성 요청
    Client->>Controller: POST /api/routine/exercise/set
    Controller->>Service: createExerciseSet(request)
    Service->>Repository: 운동 조회
    Repository->>Service: 운동 정보
    Service->>Repository: 운동 세트 저장
    Repository->>Service: 저장된 운동 세트
    Service->>Controller: 응답 생성
    Controller->>Client: 201 Created
    Client->>User: 결과 표시
    
    %% 운동 세트 조회
    User->>Client: 운동 세트 조회 요청
    Client->>Controller: GET /api/routine/exercise/set/{id}
    Controller->>Service: getExerciseSet(id)
    Service->>Repository: 운동 세트 조회
    Repository->>Service: 운동 세트 정보
    Service->>Controller: 응답 생성
    Controller->>Client: 200 OK
    Client->>User: 결과 표시
    
    %% 운동 세트 목록 조회
    User->>Client: 운동 세트 목록 조회 요청
    Client->>Controller: GET /api/routine/exercise/set?exercise_id={id}
    Controller->>Service: getExerciseSetList(exerciseId, page, size)
    Service->>Repository: 운동 세트 목록 조회
    Repository->>Service: 운동 세트 목록
    Service->>Controller: 응답 생성
    Controller->>Client: 200 OK
    Client->>User: 결과 표시
    
    %% 운동 세트 수정
    User->>Client: 운동 세트 수정 요청
    Client->>Controller: PUT /api/routine/exercise/set
    Controller->>Service: updateExerciseSet(request)
    Service->>Repository: 운동 세트 조회
    Repository->>Service: 운동 세트 정보
    Service->>Repository: 운동 세트 업데이트
    Repository->>Service: 업데이트된 운동 세트
    Service->>Controller: 응답 생성
    Controller->>Client: 200 OK
    Client->>User: 결과 표시
```

## 🟠 db 구성

### 🟢 ERD

```mermaid
---
title: oun 데이터베이스 ERD
---

erDiagram
    USERS {
        bigint user_id PK "자동 증가"
        varchar email UK "이메일"
        varchar name "사용자 이름"
        varchar profile_image "프로필 이미지"
        varchar role "권한"
        timestamp created_at "생성일시"
        timestamp updated_at "수정일시"
    }
    
    ROUTINE {
        bigint routine_id PK "자동 증가"
        bigint user_id FK "사용자 ID"
        varchar name "루틴 이름"
        varchar description "루틴 설명"
        enum status "루틴 상태(ACTIVE, INACTIVE)"
        timestamp created_at "생성일시"
        timestamp updated_at "수정일시"
    }
    
    EXERCISE {
        bigint exercise_id PK "자동 증가"
        bigint routine_id FK "루틴 ID"
        varchar name "운동 이름"
        varchar description "운동 설명"
        int sort "순서"
        enum status "운동 상태(ACTIVE, INACTIVE)"
        enum type "운동 타입(WEIGHT, CARDIO)"
        timestamp created_at "생성일시"
        timestamp updated_at "수정일시"
    }

    EXERCISE_SET {
        bigint exercise_set_id PK "자동 증가"
        bigint exercise_id FK "운동 ID"
        int number "세트 번호"
        int count "세트당 개수"
        int weight "세트 무게(kg)"
        int distance "세트 거리(m)"
        int time "세트 시간(초)"
        double speed "세트 속도(km/h)"
        varchar description "세트 설명"
        enum status "세트 상태(ACTIVE, INACTIVE)"
        timestamp created_at "생성일시"
        timestamp updated_at "수정일시"
    }
    
    USERS ||--o{ ROUTINE : "1:N 관계"
    ROUTINE ||--o{ EXERCISE : "1:N 관계"
    EXERCISE ||--o{ EXERCISE_SET : "1:N 관계"
```

## 🟠 skill set

`spring boot` `docker` `docker compose` `redis` `mysql` `swagger` `traefik` `java21` `gradle` `jpa` `querydsl`