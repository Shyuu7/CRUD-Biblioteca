FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

COPY target/*.jar app.jar

EXPOSE 7000

ENTRYPOINT ["java", "-jar", "app.jar"]