FROM openjdk:8

RUN mkdir /app

WORKDIR /app

ADD ./target/katalog_profilov-1.0-SNAPSHOT.jar /app

EXPOSE 8080

CMD ["java", "-jar", "katalog_profilov-1.0-SNAPSHOT.jar"]