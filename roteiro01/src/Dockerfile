FROM ubuntu:latest as build
RUN apt-get update && apt-get upgrade -y
RUN apt-get install -y openjdk-11-jdk
COPY ./src /usr/src/app
RUN apt-get install -y maven
WORKDIR /usr/src/app
RUN mvn clean package

FROM openjdk:11-jre-slim
EXPOSE 8080
COPY --from=build /usr/src/app/target/your-application.jar /usr/app/your-application.jar
ENTRYPOINT ["java", "-jar", "/usr/app/your-application.jar"]
