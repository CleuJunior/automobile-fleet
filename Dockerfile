FROM openjdk:17-alpine
ADD ./target/Automobile-Fleet-0.0.1-SNAPSHOT.jar automobile.jar
CMD ["java", "-jar", "/automobile.jar"]
EXPOSE 8000
