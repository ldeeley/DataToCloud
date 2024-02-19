FROM openjdk:17
LABEL authors="leste"
EXPOSE 8080
ADD target/data_to_cloud.jar data_to_cloud.jar
ENTRYPOINT ["java", "-jar", "/datatocloud.jar"]