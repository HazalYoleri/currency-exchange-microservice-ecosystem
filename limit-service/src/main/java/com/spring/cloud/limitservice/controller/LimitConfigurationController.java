package com.spring.cloud.limitservice.controller;

import com.spring.cloud.limitservice.bean.LimitConfiguration;
import com.spring.cloud.limitservice.config.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimitConfigurationController {
@Autowired
Configuration configuration;

    @GetMapping("/limits")
    public LimitConfiguration retrieveLimitFromConf(){


        return new LimitConfiguration(configuration.getMin(),configuration.getMax());
    }

}
