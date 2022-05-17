FROM openjdk:17-jdk-alpine3.14

COPY "./target/katalogus.jar" "/application/katalogus.jar"

CMD ["java", "-jar", "/application/katalogus.jar"]