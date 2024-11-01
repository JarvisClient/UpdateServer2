FROM gradle:7.6.1-jdk17 AS builder

WORKDIR /app

COPY . .

RUN gradle build

EXPOSE 8080

CMD ["java", "-jar", "build/libs/com.jnsaph.jarvis-update-server-all.jar"]
