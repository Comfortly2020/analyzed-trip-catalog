FROM adoptopenjdk:14.0.2_12-jre-openj9-0.21.0-bionic

RUN mkdir /app

WORKDIR /app

ADD ./api/target/analyzed-trip-catalog-api-1.0.0-SNAPSHOT.jar /app

EXPOSE 8080

CMD ["java", "-jar", "analyzed-trip-catalog-api-1.0.0-SNAPSHOT.jar"]