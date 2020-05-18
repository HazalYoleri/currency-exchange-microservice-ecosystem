# Currency Exchange Microservice Ecosystem

Currency Exchange Microservice Ecosystem is a basic example for microservice architecture implementation by using Spring Cloud and Netflix OSS components.

## Installation

Docker implementation is on the way :)

## Overview

Currency exchange microservices-based system consists of the following modules:

* ### Zuul Proxy

a module that Spring Cloud Netflix Zuul for running Spring Boot application that acts as a proxy/gateway.


* ### Config Server

Spring Cloud Config is Spring's client/server approach for storing and serving distributed configurations across multiple applications and environments.

This configuration store is ideally versioned under Git version control and can be modified at application runtime. Our config store is located in other [repo](https://github.com/HazalYoleri/config-store). To change the location of the repository, you can set the `spring.cloud.config.server.git.uri `configuration property in the Config Server bootstrap.properties. If you set it with a file: prefix, it should work from a local repository so that you can get started quickly and easily without a server. But you can also read configuration file from remote repo as well.

* ### Service Discovery - Netflix Eureka 
Eureka Server is in itself a microservice to which all other microservices registers.Think of it as a lookup service where microservices (clients) can register themselves and discover other registered microservices. When a client microservice registers with Eureka it provides metadata such as host, port, and health indicator thus allowing for other microservices to discover it.

* ### Cloud Bus

By using Spring Boot Actuator, we can refresh clients. However, in the cloud environment, we would need to go to every single client and reload configuration by accessing actuator endpoint.

To solve this problem, this project uses Spring Cloud Bus.

//wip..

* ### Tracing - Zipkin
Zipkin is an open source project that provides mechanisms for sending, receiving, storing, and visualizing traces. This allows us to correlate activity between servers and get a much clearer picture of exactly what is happening in our services.

In this project Zipkin connected to the in-memory database. All the microservices will put the messages in the RabbitMQ server and  Zipkin consumes the messages from the RabbitMQ server.

* #### RabbitMQ

RabbitMQ is a message-queueing software also known as a message broker or queue manager.

In this project, RabbitMQ works with Zipkin and Cloud Bus for log tracing and reloading central configuration.
![architecture](https://user-images.githubusercontent.com/39515623/82160875-f3c22e00-98a0-11ea-8551-042bab5af60b.png)
```
import foobar

foobar.pluralize('word') # returns 'words'
foobar.pluralize('goose') # returns 'geese'
foobar.singularize('phenomena') # returns 'phenomenon'
```

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

## License
[MIT](https://choosealicense.com/licenses/mit/)
