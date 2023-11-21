# Dockerfile
FROM openjdk:17
ARG JAR_FILE=out/artifacts/TeamProJect_jar2/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]