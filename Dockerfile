# ---------- STAGE 1: build ----------
FROM maven:3.9.9-eclipse-temurin-21 AS builder

WORKDIR /build

# Copia só o pom primeiro pra aproveitar cache de dependências
COPY pom.xml .
RUN mvn -q dependency:go-offline

# Agora copia o código e builda
COPY src ./src
RUN mvn -q clean package -DskipTests

# ---------- STAGE 2: runtime ----------
FROM eclipse-temurin:21-jre

WORKDIR /app

# Copia o jar gerado no build
COPY --from=builder /build/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
