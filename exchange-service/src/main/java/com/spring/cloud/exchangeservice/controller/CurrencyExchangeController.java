package com.spring.cloud.exchangeservice.controller;

import com.spring.cloud.exchangeservice.dto.ExchangeValue;
import com.spring.cloud.exchangeservice.service.CurrencyExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/currency")
@RestController
public class CurrencyExchangeController {

    @Autowired
    CurrencyExchangeService currencyExchangeService;


    @GetMapping()
    public ExchangeValue retrieveLatestValue() {
        return currencyExchangeService.getCurrenciesOfToday();

    }

    @GetMapping("/from/{from}/to/{to}")
    public ExchangeValue retrieveConversionRate
            (@PathVariable String from, @PathVariable String to) {
        return currencyExchangeService.convertCurrency(from, to);

    }

    @GetMapping("/{date}")
    public ExchangeValue retrieveHistoricalRates(@PathVariable String date) {
        return currencyExchangeService.getHistoricalCurrency(date);

    }

    @GetMapping("/{date}/from/{from}/to/{to}")
    public ExchangeValue retrieveHistoricalRates(@PathVariable String date, @PathVariable String from, @PathVariable String to) {
        return currencyExchangeService.convertHistoricalCurrency(date, from, to);

    }
}
