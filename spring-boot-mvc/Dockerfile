FROM openjdk:21-jdk-slim

RUN apt-get update \
    && apt-get install -y default-mysql-client

ARG JAR_FILE=target/spring-boot-mvc-0.0.1-SNAPSHOT.jar

ADD ${JAR_FILE} app.jar

COPY wait-for-db.sh wait-for-db.sh
RUN chmod +x wait-for-db.sh

COPY entrypoint.sh entrypoint.sh
RUN chmod +x entrypoint.sh

ENTRYPOINT ["./entrypoint.sh"]
