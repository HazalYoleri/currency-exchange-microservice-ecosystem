package com.cloud.calculationservice.controller;

import com.cloud.calculationservice.client.IExchangeServiceFeignClient;
import com.cloud.calculationservice.dto.CurrencyResult;
import com.cloud.calculationservice.service.CalculationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;


@RestController
@RequestMapping("/exchange")
@RefreshScope
public class CalculationController {

  private Logger logger = LoggerFactory.getLogger(this.getClass());


  private IExchangeServiceFeignClient exchangeServiceFeignClient;

  private CalculationService calculationService;

  @Autowired
  public CalculationController(
      CalculationService calculationService, IExchangeServiceFeignClient exchangeServiceFeignClient) {

    this.calculationService = calculationService;
    this.exchangeServiceFeignClient = exchangeServiceFeignClient;
  }


  @PostMapping("/today")
  public String getCalculatedExchangeValue(
      @RequestParam String from, @RequestParam String to, @RequestParam BigDecimal value) {
    CurrencyResult currencyResult = exchangeServiceFeignClient.retrieveConversionRate(from, to);
    currencyResult.setExchangeValue(value);
    currencyResult.setConvertedCurrency(to);
    logger.info("{}", currencyResult);
    return calculationService.calculateExchangeResult(currencyResult).toString();
  }


  @PostMapping("/historical")
  public BigDecimal getHistoricalExchangeValue(
      @RequestParam String from, @RequestParam String to, @RequestParam BigDecimal value,
      @RequestParam String date) {
    CurrencyResult currencyResult =
        exchangeServiceFeignClient.retrieveHistoricalRates(date, from, to);
    currencyResult.setExchangeValue(value);
    currencyResult.setConvertedCurrency(to);
    logger.info("Request : " + currencyResult.toString());
    return calculationService.calculateExchangeResult(currencyResult);


  }


}
