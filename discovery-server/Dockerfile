FROM openjdk:8-jdk-alpine
MAINTAINER hazalyoleri
ADD /target/discovery-server-0.0.1-SNAPSHOT.jar discovery-server.jar
ENTRYPOINT ["java","-jar","/discovery-server.jar"]
EXPOSE 5555