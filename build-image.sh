# !/bin/bash
set +x

./gradlew clean build

JAR=$1

echo $JAR

docker build -t "poss11111/clash-bot-ws-spring-boot-consumer:1.0.0" . --build-arg JAR=$JAR