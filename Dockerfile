FROM openjdk:17-slim-buster
LABEL authors="xuanpt2"
WORKDIR /app
ADD /target/slog-java-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8081

ENTRYPOINT ["java", "-jar"]
CMD ["app.jar"]
