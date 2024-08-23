#Build docker
FROM openjdk:17-alpine AS builder
WORKDIR /app
COPY . .
RUN ./gradlew clean bootJar
COPY build/libs/*.jar app.jar

#Run docker
FROM openjdk:17-alpine AS runner
WORKDIR /app
COPY --from=builder /app/app.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
