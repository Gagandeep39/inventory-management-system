FROM openjdk:8-jdk-alpine
MAINTAINER singh.gagandeep3911@gmail.com
ADD target/*.jar app.jar 
EXPOSE 9500
ENTRYPOINT [ "java", "-jar", "app.jar" ]