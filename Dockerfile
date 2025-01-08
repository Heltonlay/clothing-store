FROM openjdk:21-jdk
COPY ./target/clothing-store-0.0.1-SNAPSHOT.jar app.jar
CMD ["java", "-jar", "app.jar" ]