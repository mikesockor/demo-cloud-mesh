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

you have to find details as follows
```
Instances currently registered with Eureka
Application	AMIs	Availability Zones	Status
SERVERLESS-EXAMPLE	n/a (2)	(2)	UP (2) - 
NB3021821768.corp.emirates.com:serverless-example:8082 , 
NB3021821768.corp.emirates.com:serverless-example:8081
```

### 1 day

open in browser <br>
http://localhost:8761/

find 3 instances (2 of microservice1, 1 of gateway)

get direct access to microservice1
```
http://localhost:8081/hello
http://localhost:8081/hello,uppercase
```

get access to microservice1 though GATEWAY
```
http://localhost:8080/SERVERLESS-EXAMPLE/hello
http://localhost:8080/SERVERLESS-EXAMPLE/hello,uppercase
```

have same result   
