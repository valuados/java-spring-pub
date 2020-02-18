FROM openjdk:11
MAINTAINER valuados@gmail.com
COPY target/java-training-example-0.0.1-SNAPSHOT.jar /opt/java-training-example.jar
ENTRYPOINT ["java","-jar","/opt/java-training-example.jar"]