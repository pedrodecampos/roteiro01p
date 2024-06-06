# Etapa 1: Build da aplicação
FROM ubuntu:latest as build

# Atualizar pacotes e instalar OpenJDK 11
RUN apt-get update && apt-get upgrade -y
RUN apt-get install -y openjdk-11-jdk

# Instalar Maven
RUN apt-get install -y maven

# Definir o diretório de trabalho no contêiner
WORKDIR /app

# Copiar o arquivo pom.xml e a pasta src para o contêiner
COPY pom.xml .
COPY src ./src

# Compilar o projeto e gerar o arquivo .jar
RUN mvn clean package

# Etapa 2: Runtime
FROM openjdk:11-jre-slim

# Definir o diretório de trabalho no contêiner
WORKDIR /app

# Expor a porta para acesso externo
EXPOSE 8080

# Copiar o arquivo .jar gerado na etapa de build
COPY --from=build /app/target/*.jar /app/my-application.jar

# Comando para executar o .jar
ENTRYPOINT ["java", "-jar", "/app/my-application.jar"]
