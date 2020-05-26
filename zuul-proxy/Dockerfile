FROM openjdk:8-jdk-alpine
ADD /target/zuul-proxy-0.0.1-SNAPSHOT.jar zuul-proxy.jar
ENTRYPOINT ["java","-jar","/zuul-proxy.jar"]
EXPOSE 8085