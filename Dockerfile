# Build
FROM eclipse-temurin:21-jdk-alpine AS build

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN apk add --no-cache maven && \
    mvn clean package -DskipTests && \
    echo "=== Listando arquivos no target ===" && \
    ls -la target/ && \
    echo "=== Verificando MANIFEST do JAR ===" && \
    jar tf target/CRUDBiblioteca-1.0-SNAPSHOT.jar | grep -E "(MANIFEST|Main)" || true

# Runtime
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

COPY --from=build /app/target/CRUDBiblioteca-1.0-SNAPSHOT.jar app.jar

RUN ls -la /app/ && \
    echo "=== Verificando MANIFEST no container ===" && \
    jar tf app.jar | head -10

ENV JAVALIN_HOST=0.0.0.0 \
    JAVALIN_PORT=7000

EXPOSE 7000

ENTRYPOINT ["java", "-cp", "app.jar", "com.br.infnet.app.Main"]
