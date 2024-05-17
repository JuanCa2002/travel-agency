FROM eclipse-temurin:17
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} authentication-management.jar
ENTRYPOINT ["java", "-jar", "/authentication-management.jar"]