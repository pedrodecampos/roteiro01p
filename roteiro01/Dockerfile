# Definição da imagem com Ubuntu
FROM ubuntu:latest as build

# Atualização do ambiente
RUN apt-get update && apt-get upgrade -y

# Instalação do OpenJDK
RUN apt-get install -y openjdk-11-jdk

# Cópia do SRC para o container
COPY ./src /usr/src/app

# Instalação do Maven
RUN apt-get install -y maven

# Solicitação para gerar arquivo JAR
WORKDIR /usr/src/app
RUN mvn clean package

# Escolha da imagem que vai executar nossa aplicação
FROM openjdk:11-jre-slim

# Exposição da porta 8080 para acesso externo
EXPOSE 8080

# Cópia do .jar para execução
COPY --from=build /usr/src/app/target/your-application.jar /usr/app/your-application.jar

# Execução do .jar pela máquina virtual Java
ENTRYPOINT ["java", "-jar", "/usr/app/your-application.jar"]
