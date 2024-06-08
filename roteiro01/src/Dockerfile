# Etapa de build
FROM maven:3.8.5-openjdk-11 AS build

# Definir diretório de trabalho
WORKDIR /app

# Copiar o arquivo pom.xml e a pasta src para o contêiner
COPY pom.xml .
COPY src ./src

# Compilar o projeto e gerar o arquivo .jar
RUN mvn clean package

# Etapa de runtime
FROM openjdk:11-jre-slim

# Definir o diretório de trabalho no contêiner


# Expor a porta para acesso externo
EXPOSE 8080

COPY --from=build target/roteiro01-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java","-jar","app.jar"]

