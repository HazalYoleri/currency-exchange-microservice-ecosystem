package com.spring.cloud.exchangeservice.dto;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

@Component
public class ExchangeValue {

    private String base;
    private Map<String,String> rates;
    private Date date;

    public ExchangeValue() {
    }

    public ExchangeValue(Long id, String from, String to, BigDecimal conversionValue) {
        super();
        this.setBase(getBase());
        this.setRates(getRates());
        this.setDate(getDate());
    }


    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public Map<String, String> getRates() {
        return rates;
    }

    public void setRates(Map<String, String> rates) {
        this.rates = rates;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
