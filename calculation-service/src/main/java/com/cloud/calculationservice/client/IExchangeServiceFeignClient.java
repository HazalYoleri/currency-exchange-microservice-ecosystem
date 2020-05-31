package com.cloud.calculationservice.client;

import com.cloud.calculationservice.config.FeignClientConfiguration;
import com.cloud.calculationservice.config.RibbonConfiguration;
import com.cloud.calculationservice.dto.CurrencyResult;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@FeignClient(name = "zuul-proxy", configuration = FeignClientConfiguration.class)
@RequestMapping("exchange-service/currency")
@RibbonClient(name = "exchange-service", configuration = RibbonConfiguration.class)
@RefreshScope
public interface IExchangeServiceFeignClient {

  @GetMapping("/from/{from}/to/{to}")
  ResponseEntity<CurrencyResult> retrieveConversionRate(
      @PathVariable("from") String from, @PathVariable("to") String to);


  @GetMapping("/{date}/from/{from}/to/{to}")
  ResponseEntity<CurrencyResult> retrieveHistoricalRates(
      @PathVariable("date") String date, @PathVariable("from") String from,
      @PathVariable("to") String to);
}
