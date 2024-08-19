FROM openjdk:17
ADD target/swigato-0.0.1-SNAPSHOT.jar swigato-1.0.0.jar
EXPOSE 8088
ENTRYPOINT [ "java", "-jar", "swigato-1.0.0.jar" ]