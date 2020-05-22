package com.cloud.calculationservice.config;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.IPing;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.PingUrl;
import com.netflix.loadbalancer.WeightedResponseTimeRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

public class RibbonConfiguration {

  private IClientConfig ribbonClientConfig;

  @Autowired
  public RibbonConfiguration(IClientConfig ribbonClientConfig) {

    this.ribbonClientConfig = ribbonClientConfig;
  }

  @Bean
  public IPing ribbonPing(IClientConfig config) {
    return new PingUrl();
  }

  @Bean
  public IRule ribbonRule(IClientConfig config) {
    return new WeightedResponseTimeRule();
  }
}
