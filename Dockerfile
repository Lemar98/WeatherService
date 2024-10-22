FROM maven:3.9.9-amazoncorretto-21-al2023 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM alpine/java:21-jre
WORKDIR /app
COPY --from=build /app/target/weatherservice-*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
