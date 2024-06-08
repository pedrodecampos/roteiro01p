# Etapa de build
FROM maven:3.8.5-openjdk-11 AS build

# Definir diretório de trabalho

# Copiar o arquivo pom.xml e a pasta src para o contêiner
COPY .. .

# Compilar o projeto e gerar o arquivo .jar
RUN mvn clean package

# Etapa de runtime
FROM openjdk:11-jre-slim

# Definir o diretório de trabalho no contêiner

# Expor a porta para acesso externo
EXPOSE 8080

# Copiar o arquivo .jar gerado na etapa de build
COPY --from=build /app/target/*.jar /app/my-application.jar

# Comando para executar o .jar
ENTRYPOINT ["java", "-jar", "/app/my-application.jar"]
