package com.spring.cloud.exchangeservice.dto;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

@Component
public class ExchangeValue {

  private String base;
  private Map<String, BigDecimal> rates;
  private Date date;

  public ExchangeValue() {
  }

  public ExchangeValue(Date date, Map<String, BigDecimal> rates, String base) {
    super();
    this.base = base;
    this.rates = rates;
    this.date = date;
  }


  public String getBase() {
    return base;
  }

  public void setBase(String base) {
    this.base = base;
  }

  public Map<String, BigDecimal> getRates() {
    return rates;
  }

  public void setRates(Map<String, BigDecimal> rates) {
    this.rates = rates;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }
}
