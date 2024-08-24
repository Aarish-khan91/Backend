# Use an official OpenJDK runtime as the base image
FROM openjdk:17-jdk-alpine

# Expose the port your application runs on
EXPOSE 8080

# Add the built JAR file to the container
ADD target/Courses-Details-0.0.1-SNAPSHOT.jar /Courses-Details-0.0.1-SNAPSHOT.jar

# Command to run the JAR file
ENTRYPOINT ["java", "-jar", "/Courses-Details-0.0.1-SNAPSHOT.jar"]
