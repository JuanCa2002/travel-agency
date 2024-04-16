FROM eclipse-temurin:17
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} user-management.jar
ENTRYPOINT ["java", "-jar", "/user-management.jar"]