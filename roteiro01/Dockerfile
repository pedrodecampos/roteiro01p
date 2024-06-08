# Etapa de build
FROM ubuntu:lastest AS build

RUN apt-get update
RUN apt-get install openjdk-21-jdk -y
COPY .. .


RUN apt-get install maven -y
RUN mvn clean install



# Etapa de runtime
FROM openjdk:21-jre-slim

# Definir o diretório de trabalho no contêiner

# Expor a porta para acesso externo
EXPOSE 8080

# Copiar o arquivo .jar gerado na etapa de build
COPY --from=build /app/target/*.jar /app/my-application.jar

# Comando para executar o .jar
ENTRYPOINT ["java", "-jar", "/app/my-application.jar"]
