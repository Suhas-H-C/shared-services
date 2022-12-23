FROM openjdk:latest
WORKDIR /home/app
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
COPY src ./src
RUN ./mvnw dependency:resolve
RUN ./mvnw clean install
EXPOSE 8443
RUN echo "Jar file built successfully at /home/app"
CMD ["java","-jar","/home/app/shared-services.jar"]