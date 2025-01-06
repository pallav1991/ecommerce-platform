FROM openjdk:17
ADD target/auth-service-0.0.1-SNAPSHOT.jar auth-service.jar
EXPOSE 8090
ENTRYPOINT ["java","-jar","auth-service.jar"]