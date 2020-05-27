FROM openjdk:8-jdk-alpine
ADD /target/calculation-service-0.0.1-SNAPSHOT.jar calculation-service.jar
ENTRYPOINT ["java","-jar","/calculation-service.jar"]
EXPOSE 8100