FROM openjdk:17
ARG JAR_FILE=target/*.jadr
COPY ./target/spring-boot-docker.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]