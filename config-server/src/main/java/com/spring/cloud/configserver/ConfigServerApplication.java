package com.spring.cloud.configserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableAutoConfiguration
@SpringBootApplication
@EnableConfigServer
public class ConfigServerApplication {


    public static void main(String[] args) {

        SpringApplication.run(ConfigServerApplication.class, args);

    }

}
