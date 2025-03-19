# 🔴 project 실행

## 🟠 환경 변수

프로젝트 폴더 내 `.env` 파일을 생성하여 아래 환경 변수를 설정해주세요.

```
CONTAINER_NAME=oun-auth
SERVER_PROFILE=dev
GOOGLE_CLIENT_ID=your_google_client_id
GOOGLE_CLIENT_SECRET=your_google_client_secret
JWT_SECRET_KEY=your_jwt_secret_key
MYSQL_USER=your_db_user
MYSQL_PASSWORD=your_db_user_password
MYSQL_ROOT_PASSWORD=your_db_root_password

# 도메인 관련 환경변수
DOMAIN_MAIN=your.domain
DOMAIN_TRAEFIK=traefik.your.domain
ACME_EMAIL=your@email.com
```

## 🟠 실행

`local`
```
./gradlew clean build
docker-compose -f ./traefik/docker-compose-local up -d --build
```

`prod`
```
./gradlew clean build
docker-compose -f ./traefik/docker-compose-prod up -d --build
```