package com.spring.cloud.calculationservice.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;


public class CurrencyResult {
  private String base;
  private Map<String, BigDecimal> rates;
  private Date date;
  private BigDecimal exchangeValue;
  private String convertedCurrency;


  public CurrencyResult() {
  }

  public CurrencyResult(Date date, Map<String, BigDecimal> rates, String base) {
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

  public BigDecimal getExchangeValue() {
    return exchangeValue;
  }

  public void setExchangeValue(BigDecimal exchangeValue) {
    this.exchangeValue = exchangeValue;
  }

  public String getConvertedCurrency() {
    return convertedCurrency;
  }

  public void setConvertedCurrency(String convertedCurrency) {
    this.convertedCurrency = convertedCurrency;
  }

  @Override
  public String toString() {
    return "CurrencyResult{"
        +
        "base='" + base + '\''
        +
        ", rates=" + rates
        +
        ", date=" + date
        +
        ", exchangeValue=" + exchangeValue
        +
        ", convertedCurrency='" + convertedCurrency + '\''
        +
        '}';
  }
}
