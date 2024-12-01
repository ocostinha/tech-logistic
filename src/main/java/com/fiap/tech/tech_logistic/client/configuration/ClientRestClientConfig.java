package com.fiap.tech.tech_logistic.client.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class ClientRestClientConfig {

    @Value("${client.url}")
    private String urlBase;

    @Bean
    public RestClient restClientPncp() {
        return RestClient.builder().baseUrl(urlBase).build();
    }

}
