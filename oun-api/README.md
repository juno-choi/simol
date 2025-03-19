# 🔴 project 실행

## 🟠 환경 변수

프로젝트 폴더 내 `.env` 파일을 생성하여 아래 환경 변수를 설정해주세요.

```
SERVER_PROFILE=your_profile
JWT_SECRET_KEY=your_jwt_secret_key
MYSQL_USER=your_mysql_user
MYSQL_PASSWORD=your_mysql_user_password
MYSQL_ROOT_PASSWORD=your_mysql_root_password
```

## 🟠 실행
./gradlew clean build 실행 후

`local`
```
docker-compose -f ./oun-api/docker-compose-local up -d --build
```

`prod`
```
docker-compose -f ./oun-api/docker-compose-prod up -d --build
```