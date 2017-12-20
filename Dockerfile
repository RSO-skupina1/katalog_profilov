FROM openjdk:8-jre-alpine

RUN mkdir /app

WORKDIR /app

COPY ./target target

EXPOSE 8080
CMD ["java", "-cp", "target/classes:target/dependency/*", "com.kumuluz.ee.EeApplication"]