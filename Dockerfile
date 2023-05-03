FROM openjdk:11

ARG JAR
COPY ./build/libs/* clash-bot-ws-spring-boot-consumer.jar

RUN ls -lha

ENTRYPOINT ["java","-jar","clash-bot-ws-spring-boot-consumer.jar"]