# 🔴 project 실행

## 🟠 환경 변수

프로젝트 폴더 내 `.env` 파일을 생성하여 아래 환경 변수를 설정해주세요.

```
SERVER_PROFILE=your_profile
GOOGLE_CLIENT_ID=your_google_client_id
GOOGLE_CLIENT_SECRET=your_google_client_secret
JWT_SECRET_KEY=your_jwt_secret_key
MYSQL_USER=your_mysql_user
MYSQL_PASSWORD=your_mysql_user_password
MYSQL_ROOT_PASSWORD=your_mysql_root_password
```

## 🟠 실행

./gradlew clean build 실행 후

`local`
```
./gradlew :simol-user:clean :simol-user:build
docker-compose -f ./simol-user/docker-compose-local up -d --build
```

`prod`
```
./gradlew :simol-user:clean :simol-user:build
docker-compose -f ./simol-user/docker-compose-prod up -d --build
```