package com.spring.cloud.calculationservice.client;

import com.spring.cloud.calculationservice.dto.CurrencyResult;

public class ExchangeServiceFeignClientFallback  implements IExchangeServiceFeignClient{

    @Override
    public CurrencyResult retrieveConversionRate(String from, String to) {

        return new CurrencyResult();
    }

    @Override
    public CurrencyResult retrieveHistoricalRates(String date, String from, String to) {

        return new CurrencyResult();
    }
}

