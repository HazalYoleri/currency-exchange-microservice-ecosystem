package com.cloud.calculationservice.service;

import com.cloud.calculationservice.dto.CurrencyResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CalculationService {
  private Logger logger = LoggerFactory.getLogger(this.getClass());


  public BigDecimal calculateExchangeResult(CurrencyResult result) {
    logger.info("Result : {}", result.getExchangeValue()
        .multiply(result.getRates()
            .get(result.getConvertedCurrency())));
    return result.getExchangeValue()
        .multiply(result.getRates()
            .get(result.getConvertedCurrency()));


  }
}
