FROM openjdk:11-jre-slim
COPY target/broker-*.jar /broker.jar
CMD ["java", "-jar", "/broker.jar","service.Broker"]