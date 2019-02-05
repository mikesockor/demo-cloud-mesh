# Repository for training purpose due "emirates.com cross skilled training sessions" 2019

This repository contains Netflix OSS stack, microservice examples

- application yml/properties to be loaded from configuration server
- gateway service is entry point (API PROXI)
- load balanced communications between services will happened though gateway
- services use functional approach, spring-cloud-function to expose endpoints
- extended example use reactive stack + ui in imperative, small example fully reactive
   

### 1 day (service discovery)
run your installed apps as follows
```
java -jar demo-cloud-mesh/sd/target/mesh-server-0.0.1-SNAPSHOT.jar
java -jar demo-cloud-mesh/ms1/target/serverless-1.0-SNAPSHOT.jar --server.port=8081 
java -jar demo-cloud-mesh/ms1/target/serverless-1.0-SNAPSHOT.jar --server.port=8082
```
open in browser http://localhost:8761/ <br>
you have to find details as follows
```
Instances currently registered with Eureka
Application	AMIs	Availability Zones	Status
SERVERLESS-EXAMPLE	n/a (2)	(2)	UP (2) - 
NB3021821768.corp.emirates.com:serverless-example:8082 , 
NB3021821768.corp.emirates.com:serverless-example:8081
```

### 2 day (gateway server)
open in browser http://localhost:8761/ <br>
find 3 instances (2 of microservice1, 1 of gateway) <br>
get direct access to microservice1 <br>
http://localhost:8081/hello <br> 
http://localhost:8081/hello,uppercase <br>

have same result getting access to microservice1 though GATEWAY <br>
http://localhost:8080/SERVERLESS-EXAMPLE/hello <br>
http://localhost:8080/SERVERLESS-EXAMPLE/hello,uppercase
 
read https://www.baeldung.com/spring-cloud-gateway <br>

## day 3 (configuration server)

#### properties from classpath 
start SERVERLESS-EXAMPLE, open http://localhost:8081/config <br> 
find response `local properties has been used`

#### properties from configuration server
start CONFIGURATION-SERVER using command line arguments   
`--spring.cloud.config.server.git.username=username --spring.cloud.config.server.git.password=password` 
to get access to git under your creds

get access to config server content being stored, open http://localhost:7777/serverless-example/default <br> 
find response `{"name":"serverless-example","profiles":["default"],"label":null,"version":"a6f20a7f6559e46fdc03a4114d9775030d2e3b7a","state":null,"propertySources":[{"name":"https://github.com/mikesockor/demo-cloud-mesh/cp/serverless-example.properties","source":{"info.foo":"GITHUB properties has been used, that is great by the way"}}]}`

get access to properties injected to microservice from config server, open http://localhost:8081/config <br> 
find response `GITHUB properties has been used, that is great by the way`

read https://cloud.spring.io/spring-cloud-config/multi/multi_spring-cloud-config.html

## day 4 (service-to-service communication)

run all OSS services (config-server, gateway-server, service-discovery)

#### small example

run all small services (microservice, ui), get access to <br>
http://localhost:8080/ui-example/helloClient <br> 
http://localhost:8080/ui-example/helloBuilder <br>
http://localhost:8080/ui-example/wordsClient <br>
http://localhost:8080/ui-example/profilesClient <br>

function composition in UI level <br>
http://localhost:8086/wordsClient,uppercase

function composition in downstream service level <br>
http://localhost:8080/fullProfilesClient


communication will happen as follows <br>
browser -> gateway -> service-discovery -> ui-service -> service-discovery -> gateway -> service-discovery -> small-microservice-example  
          
#### extended example 

run all extended services (sale, airport, flight, ui), get access to <br>
http://localhost:8080/
make search from MIA to JAX, play with dates