# ─── BUILD STAGE ───────────────────────────────────────────────────────────────
# Testing
#ARG BUILDPLATFORM=linux/amd64

# Production
ARG BUILDPLATFORM
FROM --platform=$BUILDPLATFORM gradle:8.3-jdk17 AS build
WORKDIR /workspace
COPY . .
RUN gradle clean build --no-daemon -x test --console=plain --stacktrace

# ─── RUNTIME STAGE ─────────────────────────────────────────────────────────────
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

COPY --from=build /workspace/build/libs/*-all.jar ./bot.jar

ENTRYPOINT ["java","-jar","bot.jar"]
