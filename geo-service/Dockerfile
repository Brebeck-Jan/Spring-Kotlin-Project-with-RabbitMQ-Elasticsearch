FROM openjdk:11.0.10-slim-buster
WORKDIR /app
COPY /build/libs/*-0.0.1-SNAPSHOT.jar .
CMD exec java -jar *.jar --debug