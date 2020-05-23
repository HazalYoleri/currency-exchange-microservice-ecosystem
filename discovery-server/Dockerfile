FROM openjdk:8-jdk-alpine
ARG JAR=target/discovery-server-0.0.1-SNAPSHOT.jar
COPY ${JAR} discovery-server.jar
ENTRYPOINT ["java","-jar","/discovery-server.jar"]
EXPOSE 5555