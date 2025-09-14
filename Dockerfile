# ---------------------------------------------------
#  Estágio 1: Compilar a aplicação com Maven e JDK 21
# ---------------------------------------------------
FROM eclipse-temurin:21-jdk-jammy AS builder

# Define o diretório de trabalho
WORKDIR /app

# Copia o Maven Wrapper e o pom.xml
COPY .mvn/ .mvn
COPY mvnw pom.xml ./

# Baixa todas as dependências do projeto. Isso cria uma camada de cache no Docker
# para acelerar builds futuros.
RUN ./mvnw dependency:go-offline

# Copia o resto do código-fonte da aplicação
COPY src ./src

# Compila a aplicação e empacota em um .jar. A flag -DskipTests pula os testes
# para acelerar o processo de build no deploy.
RUN ./mvnw package -DskipTests

# ---------------------------------------------------
#  Estágio 2: Criar a imagem final para execução com JRE 21
# ---------------------------------------------------
FROM eclipse-temurin:21-jre-jammy

WORKDIR /app

# Copia APENAS o arquivo .jar compilado do estágio de build.
# O Maven salva o .jar na pasta "target".
COPY --from=builder /app/target/*.jar app.jar

# Expõe a porta em que a aplicação Spring Boot roda
EXPOSE 8080

# Comando para iniciar a aplicação quando o container for executado
ENTRYPOINT ["java", "-jar", "app.jar"]