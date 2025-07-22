# ─── BUILD STAGE ───────────────────────────────────────────────────────────────
ARG BUILDPLATFORM
FROM --platform=$BUILDPLATFORM gradle:8.3-jdk17 AS build
WORKDIR /workspace
COPY . .
RUN gradle clean build --no-daemon -x test

# ─── RUNTIME STAGE ─────────────────────────────────────────────────────────────
FROM eclipse-temurin:17-jre
WORKDIR /app

COPY --from=build /workspace/build/libs/*-all.jar ./bot.jar

ENTRYPOINT ["java","-jar","bot.jar"]
