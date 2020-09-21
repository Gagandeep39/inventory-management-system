FROM openjdk:8-jdk-alpine
MAINTAINER singh.gagandeep3911@gmail.com
ADD target/*.jar app.jar 
EXPOSE 9100
# Here we are exclicitly specifying docer the url 
# Container does not run on localhost which cause eureka server to not comminicate with other
ENTRYPOINT [ "java", "-jar", "app.jar", "--spring.profiles.active=mysql" ]
