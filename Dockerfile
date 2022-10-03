# AS <NAME> to name this stage as maven
FROM maven:3.6.3-openjdk-11-slim AS maven

WORKDIR /app
COPY pom.xml .
COPY src ./src

RUN mvn clean package

ENTRYPOINT ["mvn","spring-boot:run"]
