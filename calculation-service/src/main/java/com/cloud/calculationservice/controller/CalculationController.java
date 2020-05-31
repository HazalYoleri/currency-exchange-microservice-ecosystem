package com.cloud.calculationservice.controller;

import com.cloud.calculationservice.client.IExchangeServiceFeignClient;
import com.cloud.calculationservice.dto.CurrencyResult;
import com.cloud.calculationservice.dto.Exchange;
import com.cloud.calculationservice.service.CalculationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;


@RestController
@RequestMapping("/exchange")
@RefreshScope
public class CalculationController {

  private Logger logger = LoggerFactory.getLogger(this.getClass());


  private IExchangeServiceFeignClient exchangeServiceFeignClient;

  private CalculationService calculationService;

  @Autowired
  public CalculationController(
      CalculationService calculationService,
      IExchangeServiceFeignClient exchangeServiceFeignClient) {

    this.calculationService = calculationService;
    this.exchangeServiceFeignClient = exchangeServiceFeignClient;

  }


  @PostMapping("/today")
  public ResponseEntity<String> getCalculatedExchangeValue(
      @RequestBody Exchange exchange) {
    ResponseEntity<CurrencyResult> response =
        exchangeServiceFeignClient
            .retrieveConversionRate(exchange.getFrom(), exchange.getTo());

    Optional<CurrencyResult> optionalCurrencyResult = Optional.ofNullable(response.getBody());
    optionalCurrencyResult.ifPresent(result -> {
      result.setExchangeValue(exchange.getValue());
      result.setConvertedCurrency(exchange.getTo());
      logger.info("{}", optionalCurrencyResult.get());
    });

    return new ResponseEntity<>(
        calculationService
            .calculateExchangeResult(
                optionalCurrencyResult
                    .orElse(null)).toString()
            .concat(" - ")
            .concat(exchange
                .getTo()), HttpStatus.OK);

  }


  @PostMapping("/historical")
  public ResponseEntity<String> getHistoricalExchangeValue(@RequestBody Exchange exchange) {
    ResponseEntity<CurrencyResult> response =
        exchangeServiceFeignClient
            .retrieveHistoricalRates(exchange.getDate(), exchange.getFrom(), exchange.getTo());
    Optional<CurrencyResult> optionalCurrencyResult = Optional.ofNullable(response.getBody());
    optionalCurrencyResult.ifPresent(result -> {
      result.setExchangeValue(exchange.getValue());
      result.setConvertedCurrency(exchange.getTo());
      logger.info("{}", optionalCurrencyResult.get());
    });

    return new ResponseEntity<>(
        calculationService
            .calculateExchangeResult(
                optionalCurrencyResult
                    .orElse(null)).toString()
            .concat(" - ")
            .concat(exchange
                .getTo()), HttpStatus.OK);
  }
}

