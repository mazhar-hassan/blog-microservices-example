FROM maven:3.8.4-amazoncorretto-11 as MAVEN_BUILD
WORKDIR /o4/dtos

COPY common-dtos/pom.xml pom.xml
RUN mvn clean package -DskipTest -DskipMain
RUN rm -r target

COPY common-dtos/src src
RUN mvn clean install

WORKDIR /o4/build

# To resolve dependencies in a safe way (no re-download when the source code changes)
COPY post-service/pom.xml pom.xml
RUN mvn clean package -DskipTest -DskipMain
RUN rm -r target

#package application
COPY post-service/src src
RUN mvn clean package

# Build actual docker-image
FROM amazoncorretto:11 as PREPARE
WORKDIR /api

# copy /o4/build/target/micro-query-service.jar /api/app.jar
COPY --from=MAVEN_BUILD /o4/build/target/micro-post-service.jar app.jar

ENTRYPOINT ["java","-jar","app.jar"]