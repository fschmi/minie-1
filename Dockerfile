FROM maven:3.6.0-jdk-8-alpine

COPY pom.xml /
COPY src /src

RUN mvn clean compile
RUN rm -r /src

CMD mvn exec:java
