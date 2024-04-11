FROM eclipse-temurin:17
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} reservation-management.jar
ENTRYPOINT ["java", "-jar", "/reservation-management.jar"]