FROM eclipse-temurin:21-jdk-jammy AS builder
WORKDIR /app
COPY . .
RUN ./gradlew clean build -x test

FROM eclipse-temurin:21-jre
WORKDIR /app

RUN groupadd --system springboot && useradd --system --gid springboot springboot
USER springboot

COPY --from=builder /app/build/libs/mudra-zero.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]