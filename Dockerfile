# --- 빌드 단계 ---
FROM gradle:8.2.1-jdk17 AS builder
WORKDIR /app
COPY . .
RUN chmod +x ./gradlew && ./gradlew clean build

# --- 실행 단계 ---
FROM openjdk:17-jdk
COPY --from=builder /app/build/libs/*SNAPSHOT.jar app.jar

# 명시적으로 dev profile 지정
ENV SPRING_PROFILES_ACTIVE=dev

ENTRYPOINT ["java", "-jar", "/app.jar"]
