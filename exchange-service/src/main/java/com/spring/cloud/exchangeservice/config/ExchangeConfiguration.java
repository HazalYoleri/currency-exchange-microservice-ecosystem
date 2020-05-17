package com.spring.cloud.exchangeservice.config;

import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.time.Duration;

@Configuration
public class ExchangeConfiguration {


    @Bean
    public RestTemplateBuilder restTemplateBuilder(@Autowired SSLContext sslContext) {
        return new RestTemplateBuilder() {
            @Override
            public ClientHttpRequestFactory buildRequestFactory() {
                return new HttpComponentsClientHttpRequestFactory(
                        HttpClients.custom().setSSLSocketFactory(
                                new SSLConnectionSocketFactory(sslContext
                                        , NoopHostnameVerifier.INSTANCE)).build());
            }
        };
    }

    @Bean
    public SSLContext insecureSslContext() throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
        return SSLContexts.custom()
                .loadTrustMaterial(null, (x509Certificates, s) -> true)
                .build();
    }

    //:TODO NEED ERROR HANDLİNG
    @Bean
    public RestTemplate restTemplate() throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        return this.restTemplateBuilder(this.insecureSslContext()).setConnectTimeout(Duration.ofSeconds(1000))
                .setReadTimeout(Duration.ofSeconds(1000))
                .build();
    }

}