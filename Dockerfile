FROM openjdk:latest
RUN mkdir /home/app
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN chmod +x mvnw
COPY src ./src
RUN ./mvnw dependency:resolve
RUN ./mvnw clean install
EXPOSE 8443
CMD ["java","-jar","/home/app/shared-services.jar"]