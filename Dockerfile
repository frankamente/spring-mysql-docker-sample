FROM openjdk:11-jdk

# Add a volume pointing to /tmp
VOLUME /tmp

# NOTE: Expose ports using docker-compose!
# Make port available to the world outside this container
EXPOSE 8080

# The application's jar file
ENV JAR_FILE=target/app.jar
ENV PROP_FILE=src/main/resources/application.properties

# Add the application's jar to the container
COPY ${JAR_FILE} /app/app.jar
COPY ${PROP_FILE} /app/application.properties

# Run the jar file
ENTRYPOINT ["java","-Xms256m","-Xmx750m","-Djava.security.egd=file:/dev/./urandom","-Dspring.config.location=/app/application.properties","-jar","/app/app.jar"]