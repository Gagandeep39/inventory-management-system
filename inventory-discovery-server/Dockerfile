FROM openjdk:8-jre-alpine
MAINTAINER singh.gagandeep3911@gmail.com
ADD target/*.jar app.jar
EXPOSE 8761
ENTRYPOINT [ "java", "-jar", "app.jar" ]
