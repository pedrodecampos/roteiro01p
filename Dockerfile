FROM ubuntu:latest AS build

RUN apt-get update
RUN apt-get install openjdk-21-openjdk -y

RUN apt-get intall maven -y
RUN mvn clean install

FROM openjdk:21-jdk-slim

EXPOSE 8080

COPY --from=build target/todo-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]