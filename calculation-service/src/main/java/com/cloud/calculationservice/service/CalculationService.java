package com.cloud.calculationservice.service;

import com.cloud.calculationservice.dto.CurrencyResult;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CalculationService {


  public BigDecimal calculateExchangeResult(CurrencyResult result) {

    return result.getExchangeValue()
        .multiply(result.getRates()
            .get(result.getConvertedCurrency()));


  }
}
