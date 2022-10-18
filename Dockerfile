FROM openjdk:latest
RUN mkdir /home/app
COPY ./src /home/app
ADD target/shared-algo.jar shared-algo.jar
ENTRYPOINT ["java","-jar","shared-algo.jar"]