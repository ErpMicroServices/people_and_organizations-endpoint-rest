FROM eclipse-temurin:20-jdk
VOLUME /tmp
COPY build/libs/people_and_organizations-endpoint-rest-1.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
