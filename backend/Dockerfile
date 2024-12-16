# Stage 1: Build the application
FROM maven:3.9.5-eclipse-temurin-17 AS build

# Set the working directory to the root of the backend project
WORKDIR /backend

# Copy the Maven project files
COPY pom.xml ./
COPY src ./src

# Cache Maven dependencies
RUN mvn dependency:go-offline -B

# Build the application
RUN mvn package -DskipTests

# Stage 2: Create the production image
FROM eclipse-temurin:17-jre-alpine

# Set the working directory for the runtime container
WORKDIR /app

# Copy the JAR file from the build stage
COPY --from=build /backend/target/*.jar app.jar

# Expose the application port (default is 8080 for Spring Boot)
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
