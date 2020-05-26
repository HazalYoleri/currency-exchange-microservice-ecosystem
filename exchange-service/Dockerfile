FROM openjdk:8-jdk-alpine
ARG JAR=target/exchange-service-0.0.1-SNAPSHOT.jar
COPY ${JAR} exchange-service.jar
ENTRYPOINT ["java","-jar","/exchange-service.jar"]
EXPOSE 8000