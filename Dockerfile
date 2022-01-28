FROM openjdk:15-alpine

WORKDIR ./

COPY ./target/*.jar /app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","/app.jar"]
