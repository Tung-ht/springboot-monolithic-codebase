# Start with a base image containing Java runtime
FROM joengenduvel/jre17

# Add Maintainer Info
LABEL maintainer="tungxtnd0@gmail.com"

# Make port 8888 available to the world outside this container
EXPOSE 8888

# The application's jar file
ARG JAR_FILE=target/api-v1.jar

# Add the application's jar to the container
ADD ${JAR_FILE} api-v1.jar

# Run the jar file 
ENTRYPOINT ["java","-jar","/api-v1.jar"]
