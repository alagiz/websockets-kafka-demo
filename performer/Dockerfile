FROM maven:3.5-jdk-8 as BUILD

COPY src /usr/src/performer/src
COPY pom.xml /usr/src/performer

RUN mvn -f /usr/src/performer/pom.xml install

FROM openjdk:8

COPY --from=BUILD /usr/src/performer/target/*.jar  /app.jar