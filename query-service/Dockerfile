FROM maven:3.8.4-amazoncorretto-11 as MAVEN_BUILD
WORKDIR /o4/dtos
VOLUME /root/.m2/repository
COPY common-dtos/pom.xml pom.xml
COPY common-dtos/src src
RUN mvn clean install


#FROM maven:3.8.4-amazoncorretto-11 as MAVEN_BUILD
WORKDIR /o4/build
#VOLUME /root/.m2/repository
#COPY pom.xml /root/.m2/repository
COPY query-service/pom.xml pom.xml
COPY query-service/src src
#RUN mvn package
RUN mvn clean package


FROM amazoncorretto:11 as PREPARE
WORKDIR /api

# copy /o4/build/target/micro-query-service.jar /api/app.jar
COPY --from=MAVEN_BUILD /o4/build/target/micro-query-service.jar app.jar

ENTRYPOINT ["java","-jar","app.jar"]