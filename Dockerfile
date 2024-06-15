FROM maven:3.9.6-amazoncorretto
WORKDIR /tmp/
COPY pom.xml /tmp/pom.xml
COPY src  /tmp/src/
RUN mvn clean install

FROM maven:3.9.6-amazoncorretto
VOLUME /tmp
ARG JAR_FILE=target/*.jar


EXPOSE 8080
ENTRYPOINT ["java", "-jar","/app.jar"]