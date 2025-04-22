# Use official Maven + JDK image to build
FROM maven:3.8.7-eclipse-temurin-17 as builder

# Set working directory
WORKDIR /app

# Copy all project files
COPY . .

# Package the project (creates a .war or .jar)
RUN mvn clean package -DskipTests

# --- Runtime Stage ---
# Use a base image for running the app (like Tomcat or Java)

# Option 1: For WAR (Servlet/JSP)
FROM tomcat:9.0-jdk17
COPY --from=builder /app/target/IseConnect.war /usr/local/tomcat/webapps/ROOT.war

# Option 2: For JAR (Spring Boot or other)
# FROM eclipse-temurin:17-jdk
# COPY --from=builder /app/target/your-project.jar app.jar
# ENTRYPOINT ["java", "-jar", "app.jar"]
