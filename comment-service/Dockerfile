FROM maven:3.8.4-amazoncorretto-11 as MAVEN_BUILD
WORKDIR /o4/dtos

COPY common-dtos/pom.xml pom.xml
RUN mvn clean package -DskipTest -DskipMain
RUN rm -r target

COPY common-dtos/src src
RUN mvn clean install

WORKDIR /o4/build

COPY comment-service/pom.xml pom.xml
RUN mvn clean package -DskipTest -DskipMain
RUN rm -r target

COPY comment-service/src src
RUN mvn clean package

# Build actual image
FROM amazoncorretto:11 as PREPARE
WORKDIR /api

# copying /o4/build/target/micro-comment-service.jar to /api/app.jar
COPY --from=MAVEN_BUILD /o4/build/target/micro-comment-service.jar app.jar

ENTRYPOINT ["java","-jar","app.jar"]