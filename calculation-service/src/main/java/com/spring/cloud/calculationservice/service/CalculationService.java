package com.spring.cloud.calculationservice.service;

import com.spring.cloud.calculationservice.dto.CurrencyResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CalculationService {
  private Logger logger = LoggerFactory.getLogger(this.getClass());


  public BigDecimal calculateExchangeResult(CurrencyResult result) {
    logger.info(new StringBuilder().append("Result :").append(result.getExchangeValue()
        .multiply(result.getRates()
            .get(result.getConvertedCurrency()))).toString());
    return result.getExchangeValue()
        .multiply(result.getRates()
            .get(result.getConvertedCurrency()));


  }
}
