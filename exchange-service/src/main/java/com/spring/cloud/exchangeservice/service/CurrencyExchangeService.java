package com.spring.cloud.exchangeservice.service;

import com.spring.cloud.exchangeservice.dto.ExchangeValue;
import com.spring.cloud.exchangeservice.util.Constants;
import com.spring.cloud.exchangeservice.util.UrlBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class CurrencyExchangeService {

    @Autowired
    RestTemplate restTemplate;

    public ExchangeValue getCurrenciesOfToday() {
        String url = new UrlBuilder().addBase(Constants.LATEST_URL).build();
        return this.restTemplate.getForObject(url, ExchangeValue.class);

    }

    public ExchangeValue getHistoricalCurrency(String date) {
        String url = new UrlBuilder().addBase(Constants.HISTORICAL_URL).append(date).build();
        return this.restTemplate.getForObject(url, ExchangeValue.class);

    }

    public ExchangeValue convertCurrency(String from, String to) {
        String url = new UrlBuilder().addBase(Constants.LATEST_URL).addParameter(Constants.BASE, from).addParameter(Constants.SYMBOLS, to).build();
        return this.restTemplate.getForObject(url, ExchangeValue.class);

    }

    public ExchangeValue convertHistoricalCurrency(String date, String from, String to) {
        String url = new UrlBuilder().addBase(Constants.HISTORICAL_URL).append(date).addParameter(Constants.BASE, from).addParameter(Constants.SYMBOLS, to).build();
        return this.restTemplate.getForObject(url, ExchangeValue.class);

    }
}

