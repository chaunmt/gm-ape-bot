# ─── BUILD STAGE ───────────────────────────────────────────────────────────────
FROM gradle:8.3-jdk17 AS build
WORKDIR /workspace
COPY . .
RUN gradle clean build --no-daemon -x test

# ─── RUNTIME STAGE ─────────────────────────────────────────────────────────────
FROM eclipse-temurin:17-jre
WORKDIR /app

COPY --from=build /workspace/build/libs/*-all.jar ./bot.jar
COPY .env .env

ENTRYPOINT ["java","-jar","bot.jar"]
