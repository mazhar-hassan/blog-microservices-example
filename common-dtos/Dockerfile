FROM maven:3.8.4-amazoncorretto-11 as MAVEN_BUILD

WORKDIR /o4/build
VOLUME /root/.m2/repository

#COPY pom.xml /root/.m2/repository
COPY pom.xml pom.xml
COPY src src
#RUN mvn package
RUN mvn clean package
