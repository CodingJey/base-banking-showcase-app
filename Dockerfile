# Choose a base OpenJDK image matching your pom.xml's Java version
# eclipse-temurin is a good choice for Temurin builds of OpenJDK
ARG JAVA_VERSION=21
FROM eclipse-temurin:${JAVA_VERSION}-jdk-jammy

# Set working directory
WORKDIR /app

# Copy the compiled JAR file from the Maven target directory into the container
# Assumes you run `mvn package` first to build the JAR
COPY target/base-banking-showcase-app-0.0.1-SNAPSHOT.jar app.jar

# Expose the port the application runs on (must match server.port in application.yml)
EXPOSE 8080

# Command to run the application when the container starts
# You might add Java memory options here for production (-Xmx, -Xms)
ENTRYPOINT ["java", "-jar", "app.jar"]