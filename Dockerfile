FROM openjdk:16-jdk-alpine
MAINTAINER Yuriev Nikita
COPY target/PasteboxFinal-0.0.1-SNAPSHOT.jar pastebox.jar
ENTRYPOINT ["java", "-jar", "/pastebox.jar"]