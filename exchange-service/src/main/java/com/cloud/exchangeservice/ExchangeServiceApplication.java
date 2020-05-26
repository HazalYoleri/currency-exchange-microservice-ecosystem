package com.cloud.exchangeservice;

import brave.sampler.Sampler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@EnableDiscoveryClient
@EnableCircuitBreaker
@SpringBootApplication

public class ExchangeServiceApplication {
  @Bean
  public Sampler defaultSampler() {
    return Sampler.ALWAYS_SAMPLE;
  }

  public static void main(String[] args) {
    SpringApplication.run(ExchangeServiceApplication.class, args);
  }

}
