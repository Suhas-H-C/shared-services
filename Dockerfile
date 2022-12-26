FROM maven:3.8.4-openjdk-17 as maven-builder
RUN mkdir /app
COPY src /app/src
COPY pom.xml /app
RUN mvn -f /app/pom.xml clean package -DskipTests
FROM openjdk:17-alpine
COPY --from=maven-builder app/target/shared-services.jar shared-services.jar
COPY src ./src
EXPOSE 8443
CMD ["java","-jar","shared-services.jar"]