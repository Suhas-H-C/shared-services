FROM openjdk:latest
RUN mkdir /home/app
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:resolve
COPY src ./src
ADD target/shared-services.jar shared-services.jar
ENTRYPOINT ["java","-jar","shared-services.jar"]