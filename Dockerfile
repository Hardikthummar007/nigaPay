FROM eclipse-temurin:17-jdk
ADD target/rest-demo11.jar rest-demo.jar
ENTRYPOINT ["java", "-jar", "rest-demo.jar"]
