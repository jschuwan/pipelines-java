#---- multi-stage dockerfile ----
#---- build stage ----
FROM maven:3.6.3-jdk-8-slim AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package
#RUN mvn install -DskipTests

#---- package stage ----
#FROM openjdk:8-jdk-alpine
#RUN addgroup -S spring && adduser -S spring -G spring
#User spring:spring
#ARG war_FILE=home/app/target/*.war
#COPY ${war_FILE} app.war
#ENTRYPOINT ["java","-jar","/app.war"]

FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY $JAR_FILE app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]


#FROM jschuwan/jschuwan:bc485abe7f7b4f47a11eba36c84169a8
#USER root
#RUN apt-get update && apt-get upgrade -y
#USER jenkins
#RUN jenkins-plugin-cli --plugins "blueocean:1.25.3 docker-workflow:1.28"
