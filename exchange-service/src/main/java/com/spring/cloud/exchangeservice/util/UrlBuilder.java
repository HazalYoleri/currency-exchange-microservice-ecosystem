package com.spring.cloud.exchangeservice.util;


public class UrlBuilder {
    private StringBuilder params;
    private StringBuilder base;


    public UrlBuilder() {
        this.base = new StringBuilder();
        this.params = new StringBuilder();

    }

    public UrlBuilder addBase(String base) {
        this.base.append(base);
        return this;
    }

    public UrlBuilder addParameter(String parameter, String value) {
        if (params.toString().length() > 0) {
            params.append("&");
        }
        params.append(parameter);
        params.append("=");
        params.append(value);
        return this;
    }

    public UrlBuilder append(String value) {
        this.base.append(value);
        return this;
    }

    public String build() {
        return this.base.append(params).toString();

    }
}