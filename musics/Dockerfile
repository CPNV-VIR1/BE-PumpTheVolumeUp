FROM openjdk:17

RUN microdnf install maven

COPY ./* /app/to-built/

WORKDIR /app/to-built
RUN mvn clean install

RUN cp target/*.jar ../app.jar

WORKDIR /app

RUN rm -rf /app/to-built

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]