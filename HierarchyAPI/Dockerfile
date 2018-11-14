FROM openjdk:8-jdk-alpine
RUN apk update && apk add bash
RUN mkdir -p /opt/app
ENV PROJECT_HOME /opt/app
COPY ./target/HierarchyAPI-0.0.1-SNAPSHOT.jar $PROJECT_HOME/hierarchy-app.jar
WORKDIR $PROJECT_HOME
CMD ["java", "-Dspring.data.mongodb.uri=mongodb://mongohost:27017/nodedb","-jar","./hierarchy-app.jar"]