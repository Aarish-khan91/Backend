# Use an official OpenJDK runtime as the base image
FROM openjdk:17-jdk-alpine

# Expose the port your application runs on
EXPOSE 8080

# Set environment variables for MySQL
ENV MYSQL_HOST=localhost
ENV MYSQL_PORT=3306
ENV MYSQL_USER=root
ENV MYSQL_PASSWORD=manager
ENV MYSQL_DB=sbdb

# Add the built JAR file to the container
ADD target/Courses-Details-0.0.1-SNAPSHOT.jar /Courses-Details-0.0.1-SNAPSHOT.jar

# Install MySQL client
RUN apk add --no-cache mysql-client

# Copy MySQL connector JAR file
COPY mysql-connector-java-8.0.28.jar /usr/local/lib/

# Set the classpath for the MySQL connector
ENV CLASSPATH=/usr/local/lib/mysql-connector-java-8.0.28.jar

# Command to run the JAR file
ENTRYPOINT ["java", "-jar", "/Courses-Details-0.0.1-SNAPSHOT.jar"]