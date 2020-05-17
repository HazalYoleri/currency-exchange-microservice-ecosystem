package com.spring.cloud.calculationservice.controller;

import com.spring.cloud.calculationservice.dto.CurrencyResult;
import com.spring.cloud.calculationservice.client.IExchangeServiceFeignClient;
import com.spring.cloud.calculationservice.service.CalculationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/exchange")

public class CalculationController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IExchangeServiceFeignClient exchangeServiceFeignClient;


    @Autowired
    private CalculationService calculationService;

    @PostMapping("/today")
    public String getCalculatedExchangeValue(@RequestParam String from, @RequestParam String to, @RequestParam BigDecimal value) {
        CurrencyResult currencyResult = exchangeServiceFeignClient.retrieveConversionRate(from, to);
        currencyResult.setExchangeValue(value);
        currencyResult.setConvertedCurrency(to);
        logger.info("{}", currencyResult);
        return calculationService.calculateExchangeResult(currencyResult).toString();
    }

    @PostMapping("/historical")
    public BigDecimal getHistoricalExchangeValue(@RequestParam String from, @RequestParam String to, @RequestParam BigDecimal value, @RequestParam String date) {
        CurrencyResult currencyResult = exchangeServiceFeignClient.retrieveHistoricalRates(date, from, to);
        currencyResult.setExchangeValue(value);
        currencyResult.setConvertedCurrency(to);
        logger.info("Request : " + currencyResult.toString());
        return calculationService.calculateExchangeResult(currencyResult);


    }


}
