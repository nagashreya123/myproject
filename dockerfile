# Use Java 17 runtime
FROM eclipse-temurin:17-jre

# Set working directory
WORKDIR /app

# Copy the Spring Boot jar
COPY target/My_App-0.0.1-SNAPSHOT.jar app.jar

# Expose application port
EXPOSE 8081

# Start the application
ENTRYPOINT ["java","-jar","app.jar"]
