package com.cloud.calculationservice.client;

import com.cloud.calculationservice.dto.CurrencyResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ExchangeServiceFeignClientFallback implements IExchangeServiceFeignClient {

  @Override
  public ResponseEntity<CurrencyResult> retrieveConversionRate(String from, String to) {

    return new ResponseEntity<>(new CurrencyResult(), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<CurrencyResult> retrieveHistoricalRates(
      String date, String from, String to) {

    return new ResponseEntity<>(new CurrencyResult(), HttpStatus.OK);
  }
}

