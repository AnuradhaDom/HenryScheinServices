FROM openjdk:8

# Expose port 8080 to the outside world
EXPOSE 8080

# Copy the JAR file into the container
ADD target/henryscheinproject.jar /henryscheinproject.jar

# Set the entry point for the container
ENTRYPOINT ["java", "-jar", "/henryscheinproject.jar"]
