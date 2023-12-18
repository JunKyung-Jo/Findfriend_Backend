#FROM openjdk:17-oracle
FROM adoptopenjdk:17-jdk-hotspot-buster-armv7

WORKDIR /app

ARG JAR_FILE=build/libs/*.jar

COPY ${JAR_FILE} app.jar

EXPOSE 8080

CMD ["java", "-ja   r", "app.jar"]