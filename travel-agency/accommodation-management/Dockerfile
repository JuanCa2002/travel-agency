FROM eclipse-temurin:17
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} accommodation-management.jar
ENTRYPOINT ["java", "-jar", "/accommodation-management.jar"]