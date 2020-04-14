FROM adoptopenjdk/openjdk11:alpine-jre

ARG JAR_FILE=target/order-management-api-0.0.1-SNAPSHOT.jar

WORKDIR /opt/app

COPY ${JAR_FILE} order-management.jar

ENTRYPOINT exec java $JAVA_OPTS -jar order-management.jar

