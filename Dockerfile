# ─── BUILD STAGE ───────────────────────────────────────────────────────────────
ARG BUILDPLATFORM
FROM --platform=$BUILDPLATFORM openjdk:17-jdk-alpine AS build
WORKDIR /workspace
COPY . .
RUN gradle clean build --no-daemon -x test

# ─── RUNTIME STAGE ─────────────────────────────────────────────────────────────
FROM openjdk:17-alpine
WORKDIR /app

COPY --from=build /workspace/build/libs/*-all.jar ./bot.jar

ENTRYPOINT ["java","-jar","bot.jar"]
