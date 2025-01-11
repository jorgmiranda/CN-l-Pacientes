FROM eclipse-temurin:21-jdk AS buildstage 

RUN apt-get update && apt-get install -y maven

WORKDIR /app

COPY pom.xml .
COPY src /app/src
COPY Wallet_ZGBH6XV4VHFG9ZKV /app/wallet

ENV TNS_ADMIN=/app/wallet

RUN mvn clean package

FROM eclipse-temurin:21-jdk

COPY --from=buildstage /app/target/paciente-0.0.1-SNAPSHOT.jar /app/app.jar

COPY Wallet_ZGBH6XV4VHFG9ZKV /app/wallet

ENV TNS_ADMIN=/app/wallet
EXPOSE 8081

ENTRYPOINT [ "java", "-jar","/app/app.jar" ]

#docker build --no-cache -t jorgsanchezm/producto_back:latest .
#docker run -d --name producto_back -p 8081:8081 jorgsanchezm/producto_back
#docker run -d --name producto_back -p 8081:8081 jorgsanchezm/producto_back:v2