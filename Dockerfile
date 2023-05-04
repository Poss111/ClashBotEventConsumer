FROM openjdk:11

ARG JAR
COPY ${JAR} clash-bot-ws-spring-boot-consumer.jar

ENTRYPOINT ["java","-jar","clash-bot-ws-spring-boot-consumer.jar"]