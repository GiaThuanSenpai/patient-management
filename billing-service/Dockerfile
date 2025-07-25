FROM maven:3.9.9-eclipse-temurin-21 AS builder
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline -B
COPY src ./src

RUN mvn clean package -DskipTests


FROM openjdk:21-jdk AS runner
WORKDIR /app
COPY --from=builder /app/target/billingservice-0.0.1-SNAPSHOT.jar ./app.jar

HEALTHCHECK --interval=30s --timeout=10s --start-period=5s --retries=3 \
    CMD curl -f http://localhost:8080/actuator/health || exit 1
	
ADD https://github.com/vishnubob/wait-for-it/raw/master/wait-for-it.sh /wait-for-it.sh
RUN chmod +x /wait-for-it.sh
EXPOSE 4001
EXPOSE 9001
ENTRYPOINT ["java", "-jar", "app.jar"]
