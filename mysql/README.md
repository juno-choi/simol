# 🔴 project 실행

## 🟠 환경 변수

프로젝트 폴더 내 `.env` 파일을 생성하여 아래 환경 변수를 설정해주세요.

```
MYSQL_USER=your_db_user
MYSQL_PASSWORD=your_db_user_password
MYSQL_ROOT_PASSWORD=your_db_root_password
```

## 🟠 실행

```
docker-compose -f ./mysql/docker-compose up -d --build
```
