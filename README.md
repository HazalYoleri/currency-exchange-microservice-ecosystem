# Currency Exchange Microservice Ecosystem

Currency Exchange Microservice Ecosystem is a basic example for microservice architecture implementation by using Spring Cloud and Netflix OSS components.

## Installation

Docker implementation is on the way :)

## Overview

Currency exchange microservices-based system consists of the following technologies:

* ### Zuul Proxy

a module that Spring Cloud Netflix Zuul for running Spring Boot application that acts as a proxy/gateway.


* ### Config Server

Spring Cloud Config is Spring's client/server approach for storing and serving distributed configurations across multiple applications and environments.

This configuration store is ideally versioned under Git version control and can be modified at application runtime. Our config store is located in other [repo](https://github.com/HazalYoleri/config-store). To change the location of the repository, you can set the `spring.cloud.config.server.git.uri `configuration property in the Config Server bootstrap.properties. If you set it with a file: prefix, it should work from a local repository so that you can get started quickly and easily without a server. But you can also read configuration file from remote repo as well.

* ### Service Discovery - Netflix Eureka 
Eureka Server is in itself a microservice to which all other microservices registers.Think of it as a lookup service where microservices (clients) can register themselves and discover other registered microservices. When a client microservice registers with Eureka it provides metadata such as host, port, and health indicator thus allowing for other microservices to discover it.

* ### Cloud Bus

By using Spring Boot Actuator, we can refresh clients. However, in the cloud environment, we would need to go to every single client and reload configuration by accessing actuator endpoint.

To solve this problem, this project uses Spring Cloud Bus with rabbitMQ as a message broker. If any configuration changes in config-store, and invoke `/bus-refresh `endpoint, all of the consumers will go and ask for a configuration from config-server.

* ### Tracing - Zipkin
Zipkin is an open source project that provides mechanisms for sending, receiving, storing, and visualizing traces. This allows us to correlate activity between servers and get a much clearer picture of exactly what is happening in our services.

In this project Zipkin connected to the in-memory database. All the microservices will put the messages in the RabbitMQ server and  Zipkin consumes the messages from the RabbitMQ server.

![zipkin](https://user-images.githubusercontent.com/39515623/82371753-64915380-9a23-11ea-8009-19d94ae5bfaa.png)


* #### RabbitMQ

RabbitMQ is a message-queueing software also known as a message broker or queue manager.
In this project, RabbitMQ works with Zipkin and Cloud Bus for log tracing and reloading central configuration.
* #### Hystrix
Hystrix is a library that helps you control the interactions between these distributed services by adding latency tolerance and fault tolerance logic. 


* #### Feign
Feign is a declarative web service client that makes writing web service clients easier. We use the different annotations provided by the Spring framework such as Requestmapping, @PathVariable in a Java interface to define the abstract implementation of our actual API and the Feign internally process these annotations into a templatized request to the actual web service.

* #### Ribbon
Ribbon is a client-side load balancer that gives you a lot of control over the behavior of HTTP and TCP clients. Feign already uses Ribbon, so, if you use @FeignClient, you have already client-side load balancing. But in this project even if we have a feign client in calculation-service, there is also ribbon implementation too.

* ## Architecture
![architecture](https://user-images.githubusercontent.com/39515623/82160875-f3c22e00-98a0-11ea-8551-042bab5af60b.png)
* ### Exchange-Service

| Method        | Path           | Description  |
| ------------- |-------------| -----|
| GET      | /currency/from/{from}/to/{to} |Request specific exchange rates by setting from and to parameter. |
| GET      | /currency/{date}/from/{from}/to/{to}"    |   Request specific historical exchange rates by setting from and to parameter |
| GET | /currency      |    Get the latest foreign exchange reference rates.|

* ### Calculation-Service

| Method        | Path           | Description  |
| ------------- |-------------| -----|
| POST      | /exchange/today  |Calculates according to the exchange rate |

#####

```python
Example Request:

curl -X POST \
  http://localhost:8085/calculation-service/exchange/today \
  -H 'Content-Type: application/x-www-form-urlencoded' \
  -d 'from=USD&to=TRY&value=132'
```

| Method        | Path           | Description  |
| ------------- |-------------| -----|
| POST      | /exchange/historical    | Calculates according to the exchange rate and date |

```python
Example Request:

curl -X POST \
  http://localhost:8085/calculation-service/exchange/today \
  -H 'Content-Type: application/json' \
  -d '{
    "from": "USD",
    "to": "TRY",
    "value": 1234
}'
```

#### WIP:Swagger Implementation

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

## License
[MIT](https://choosealicense.com/licenses/mit/)
