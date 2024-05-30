FROM eclipse-temurin:17
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} user-management.jar
# Add wait-for-it script and make it executable
COPY wait-for-it.sh wait-for-it.sh
RUN chmod +x wait-for-it.sh

CMD ["java", "-jar", "/user-management.jar"]