FROM openjdk:21-jdk-slim

# jar파일 복사
COPY ./build/libs/simol-user-1.0.0.jar simol-user.jar
ENTRYPOINT ["java","-jar","simol-user.jar"]
EXPOSE 8080