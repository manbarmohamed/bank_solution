FROM openjdk:21
EXPOSE 8081
ADD target/app.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]
