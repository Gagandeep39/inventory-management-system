# FROM openjdk:8-jre-alpine
# MAINTAINER singh.gagandeep3911@gmail.com
# ADD target/*.jar app.jar
# EXPOSE 9500 9550
# ENTRYPOINT [ "java", "-jar", "app.jar" ]

# Efficient Method
FROM openjdk:8-jre-alpine
LABEL maintainer="singh.gagandeep3911@gmail.com"
ARG DEPENDENCY=target/dependency
COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY ${DEPENDENCY}/META-INF /app/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /app
EXPOSE 9500 9550
ENTRYPOINT ["java","-cp","app:app/lib/*","com.cg.inventorydatabaseservice.InventoryDatabaseServiceApplication"]