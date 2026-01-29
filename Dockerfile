#FROM openjdk:11-jdk-slim
FROM openjdk:21-jdk-slim
EXPOSE 8083
ADD target/demo.jar demo.jar
ENTRYPOINT ["java", "-jar", "demo.jar"]