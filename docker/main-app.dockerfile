FROM maven:3.8.3-openjdk-17
ARG APP_VERSION="1.0-SNAPSHOT"
ENV VERSION=$APP_VERSION

COPY target/DemoJavaSpringBootApp.jar DemoJavaSpringBootApp.jar
EXPOSE 8081
CMD [ "java", "-jar", "DemoJavaSpringBootApp.jar", "--spring.profiles.active=prod"]
