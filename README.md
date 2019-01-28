# Microservice service mesh demo implementation

This repository contains Mesh Server (Eureka), service examples

### 1 day
run your installed apps as follows
```
java -jar demo-cloud-mesh/sd/target/mesh-server-0.0.1-SNAPSHOT.jar
java -jar demo-cloud-mesh/ms1/target/serverless-1.0-SNAPSHOT.jar --server.port=8081 
java -jar demo-cloud-mesh/ms1/target/serverless-1.0-SNAPSHOT.jar --server.port=8082
```
open in browser <br>
http://localhost:8761/

you have to find details similar to picture https://github.com/mikesockor/demo-cloud-mesh/blob/master/readme-pic-1.PNG
