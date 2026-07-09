FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

COPY . .

RUN ./gradlew clean build -x test

EXPOSE 8080

CMD ["java", "-jar", "build/libs/pnc-parcial-final-hotel-0.0.1-SNAPSHOT.jar"]