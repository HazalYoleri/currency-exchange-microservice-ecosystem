package com.cloud.exchangeservice.controller;

import com.cloud.exchangeservice.dto.ExchangeValue;
import com.cloud.exchangeservice.service.CurrencyExchangeService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/currency")
@RefreshScope
@RestController

public class CurrencyExchangeController {

  CurrencyExchangeService currencyExchangeService;

  @Autowired
  public CurrencyExchangeController(CurrencyExchangeService currencyExchangeService) {

    this.currencyExchangeService = currencyExchangeService;

  }

  @HystrixCommand(fallbackMethod = "fallbackFromCurrencyApi")
  @GetMapping()
  public ResponseEntity<ExchangeValue> retrieveLatestValue() {
    return new ResponseEntity<>(currencyExchangeService.getCurrenciesOfToday(), HttpStatus.OK);

  }

  @GetMapping("/from/{from}/to/{to}")
  public ResponseEntity<ExchangeValue> retrieveConversionRate(
      @PathVariable String from, @PathVariable String to) {
    return new ResponseEntity<>(currencyExchangeService.convertCurrency(from, to), HttpStatus.OK);

  }

  @GetMapping("/{date}")
  public ResponseEntity<ExchangeValue> retrieveHistoricalRates(@PathVariable String date) {
    return new ResponseEntity<>(currencyExchangeService.getHistoricalCurrency(date), HttpStatus.OK);

  }

  @GetMapping("/{date}/from/{from}/to/{to}")
  public ResponseEntity<ExchangeValue> retrieveHistoricalRates(
      @PathVariable String date, @PathVariable String from,
      @PathVariable String to) {
    return new ResponseEntity<>(
        currencyExchangeService
            .convertHistoricalCurrency(date, from, to), HttpStatus.OK);

  }

  public ExchangeValue fallbackFromCurrencyApi() {
    return new ExchangeValue();

  }


}
