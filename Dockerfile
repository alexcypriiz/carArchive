FROM openjdk:17.0.2-jdk
COPY ./target/carArchive-0.0.1-SNAPSHOT.jar /usr/app/
ENTRYPOINT [ "java", "-jar", "/usr/app/carArchive-0.0.1-SNAPSHOT.jar"]