//package com.ninyo.opensearch.config;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.web.reactive.function.client.ExchangeStrategies;
//import org.springframework.web.reactive.function.client.WebClient;
//
//public class SearchApiClientConfig {
//
//    private static final int MAX_BUFFER_SIZE = 25000 * 2024;
//
//    @Value("${search-api.url}")
//    String searchApiServerUrl;
//
//    @Bean
//    public WebClient searchApiClient() {
//        return WebClient
//                .builder()
//                .exchangeStrategies(ExchangeStrategies.builder()
//                        .codecs(codecs -> codecs.defaultCodecs().maxInMemorySize(MAX_BUFFER_SIZE))
//                        .build())
//                .baseUrl(searchApiServerUrl)
//                .build();
//    }
//}
