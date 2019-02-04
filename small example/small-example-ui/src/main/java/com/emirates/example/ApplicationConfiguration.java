package com.emirates.example;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.client.loadbalancer.reactive.LoadBalancerExchangeFilterFunction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * @author S750976
 */
@Configuration
public class ApplicationConfiguration {

    // through webClient constructor using arguments
    @Bean
    @LoadBalanced
    WebClient webClient(LoadBalancerClient loadBalancerClient) {
        return WebClient.builder()
            .filter(new LoadBalancerExchangeFilterFunction(loadBalancerClient))
            .baseUrl("http://gateway/serverless-example")
            .build()
            ;
    }

    // through webClient builder to be injected and build
    @Bean
    @LoadBalanced
    WebClient.Builder webClientBuilder() {
        return WebClient.builder()
            ;
    }

}
