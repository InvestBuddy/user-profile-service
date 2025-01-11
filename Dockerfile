# Use JDK 21 as the base image
FROM openjdk:21-jdk

# Set working directory
WORKDIR /app

# Copy the JAR file and rename it to user-profile-service.jar
COPY target/user-profile-service-1.0-SNAPSHOT.jar user-profile-service.jar

# Expose port
EXPOSE 8082

# Run the application
ENTRYPOINT ["java", "-jar", "user-profile-service.jar"]
