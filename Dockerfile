#---- multi-stage dockerfile ----
#---- build stage ----
FROM maven:3.6.3-jdk-8-slim AS build
COPY src /usr/src/app/src
RUN cd /usr/src/app/src
RUN ls
COPY pom.xml /usr/src/app
RUN mvn -f /usr/src/app/pom.xml clean package
RUN ls
#COPY /usr/src/app/target/*.jar /usr/app/app.jar 
#EXPOSE 8080
#ENTRYPOINT ["java","-jar","/usr/app/app.jar"]
#FROM openjdk:8-jdk-alpine
ARG JAR_FILE=/usr/src/app/target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
