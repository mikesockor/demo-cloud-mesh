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

### 2 day

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

read https://www.baeldung.com/spring-cloud-gateway <br>

## day 3

### local props
start MS1
```
http://localhost:8081/config
local properties has been used
```

### configuration server

using command line arguments   
`--spring.cloud.config.server.git.username=S750976 --spring.cloud.config.server.git.password=*******` 
to get access to git under your creds

get access to config server content being stored
```
http://localhost:7777/serverless-example/default
{"name":"serverless-example","profiles":["default"],"label":null,"version":"a6f20a7f6559e46fdc03a4114d9775030d2e3b7a","state":null,"propertySources":[{"name":"https://github.com/mikesockor/demo-cloud-mesh/cp/serverless-example.properties","source":{"info.foo":"GITHUB properties has been used, that is great by the way"}}]}
```

### GIT props
```
http://localhost:8081/config
GITHUB properties has been used, that is great by the way
```

read https://cloud.spring.io/spring-cloud-static/spring-cloud-config/1.3.3.RELEASE/multi/multi__spring_cloud_config_server.html