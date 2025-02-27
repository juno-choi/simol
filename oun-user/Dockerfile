FROM openjdk:21-jdk-slim

# jar파일 복사
COPY ./build/libs/oun-user-1.0.0.jar oun-user.jar
ENTRYPOINT ["java","-jar","oun-user.jar"]
EXPOSE 8080