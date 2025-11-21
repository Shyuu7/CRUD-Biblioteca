FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

COPY target/*.jar app.jar

ENV JAVALIN_HOST=0.0.0.0 \
    JAVALIN_PORT=7000

EXPOSE 7000

ENTRYPOINT ["java", "-jar", "app.jar"]