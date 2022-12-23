FROM openjdk:latest
RUN mkdir /home/app
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
COPY src ./src
COPY target ./target
RUN ./mvnw dependency:resolve
EXPOSE 8443
CMD ["java","-jar","/target/shared-services.jar"]