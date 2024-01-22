# Use a base image with a Kotlin runtime and OpenJDK
FROM openjdk:21-jdk-slim as builder

# Set the working directory
WORKDIR /app

# Copy the compiled jar file into the container
COPY build/libs/com.jnsaph.jarvis-update-server-all.jar .

# Expose the port your application listens on (if applicable)
EXPOSE 8080

# Command to run your application
CMD ["java", "-jar", "com.jnsaph.jarvis-update-server-all.jar"]
