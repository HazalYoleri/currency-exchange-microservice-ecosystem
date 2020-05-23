FROM openjdk:8-jdk-alpine
ARG JAR=target/config-server-0.0.1-SNAPSHOT.jar
COPY ${JAR} config-server.jar
ENTRYPOINT ["java","-jar","/config-server.jar"]
EXPOSE 8888