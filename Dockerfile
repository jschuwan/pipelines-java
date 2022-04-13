#---- multi-stage dockerfile ----
#---- build stage ----
FROM maven:3.6.3-jdk-8-slim AS build
COPY src /usr/src/app/src
COPY pom.xml /usr/src/app
RUN mvn -f /usr/src/app/pom.xml clean package

FROM openjdk:9
COPY --from=build /usr/src/app/target/*.jar /usr/app/app.jar 
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/app/app.jar"]
