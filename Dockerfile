FROM openjdk:11-jre-slim

COPY build/libs/*.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]

#FROM gradle:8.0.2-jdk17 AS mbuilder
#COPY ./delivery-service /usr/src/
#WORKDIR /usr/src/
#RUN gradle wrapper --gradle-version 8.0.2
#RUN ./gradlew build
#
#FROM openjdk:21-ea-17-jdk-slim
#COPY --from=mbuilder /usr/src/build/libs/delivery-service-0.0.1-SNAPSHOT.jar /usr/src/
#CMD ["java","-jar","/usr/src/delivery-service-0.0.1-SNAPSHOT.jar"]