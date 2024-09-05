FROM openjdk:21

WORKDIR /app

COPY pom.xml ./
COPY .mvn .mvn
COPY mvnw ./

COPY src ./src

RUN ./mvnw dependency:resolve

EXPOSE 8080

CMD ["./mvnw", "spring-boot:run"]