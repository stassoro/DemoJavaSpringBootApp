FROM maven:3.8.3-openjdk-17
ARG APP_VERSION="1.0-SNAPSHOT"
ARG DB_PASS="pass123!"
ARG DB_HOST="172.17.0.1"
ENV VERSION=$APP_VERSION
ENV DB_PASS=$DB_PASS
ENV DB_HOST=$DB_HOST

COPY target/DemoJavaSpringBootApp.jar DemoJavaSpringBootApp.jar
EXPOSE 8081
CMD [ "java", "-jar", "DemoJavaSpringBootApp.jar", "--spring.profiles.active=prod"]
