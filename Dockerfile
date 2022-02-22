FROM openjdk:11-jre-slim-buster
RUN addgroup -S spring && adduser -S springuser -G spring
USER springuser
WORKDIR /home/springuser
COPY build/libs/reactive-learning-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-Xmx512m", "-Xms256m", "-jar", "app.jar"]
