# 🔴 project 실행

## 🟠 환경 변수

프로젝트 폴더 내 `.env` 파일을 생성하여 아래 환경 변수를 설정해주세요.

### 🟢 oun-user 환경 변수 설정
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

## 🟠 실행

순서대로 실행하시길 추천드립니다.

### 🟢 mysql

```
docker-compose -f ./mysql/docker-compose up -d --build
```

### 🟢 redis

```
docker-compose -f ./redis/docker-compose up -d --build
```

### 🟢 traefik

```
./gradlew clean build
docker-compose -f ./traefik/docker-compose up -d --build
```

### 🟢 monitoring

```
docker-compose -f ./monitoring/docker-compose up -d --build
```

grafana template

19004 - statistics

21308 - http