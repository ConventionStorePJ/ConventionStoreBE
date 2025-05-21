# --- 빌드 단계 ---
FROM gradle:8.2.1-jdk17 AS builder
WORKDIR /app
COPY . .
RUN chmod +x ./gradlew && ./gradlew clean build

# --- 실행 단계 ---
FROM openjdk:17-jdk
COPY --from=builder /app/build/libs/*SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
