FROM openjdk:22-ea-21-slim-bullseye

# jar파일 복사
COPY build/libs/simol-1.0.jar simol.jar
ENTRYPOINT ["java","-jar","simol.jar"]
EXPOSE 8080