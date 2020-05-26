FROM openjdk:8-jdk-alpine
MAINTAINER hazalyoleri
ADD /target/config-server-0.0.1-SNAPSHOT.jar config-server.jar
ENTRYPOINT ["java","-jar","/config-server.jar"]
EXPOSE 8888