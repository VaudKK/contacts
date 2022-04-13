FROM adoptopenjdk:11-jdk
COPY target/contacts-0.0.1-SNAPSHOT.jar contacts-app-1.0.0.jar
ADD sample.db sample.db
ENTRYPOINT ["java","-jar","/contacts-app-1.0.0.jar"]