FROM openjdk:21-jdk-slim

# jar파일 복사
COPY ./build/libs/oun-api-1.0.0.jar oun-api.jar
ENTRYPOINT ["java","-jar","oun-api.jar"]
EXPOSE 8080