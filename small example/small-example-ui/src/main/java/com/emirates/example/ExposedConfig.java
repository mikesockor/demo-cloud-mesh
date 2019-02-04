package com.emirates.example;

import java.util.function.Function;
import java.util.function.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.netflix.ribbon.RibbonClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

/**
 * @author S750976
 */
@Configuration
@RibbonClients({ @RibbonClient(name = "serverless-example") })
public class ExposedConfig {

    @Autowired
    private WebClient webClient;

    @Autowired
    private WebClient.Builder webClientBuilder;

    /**
     * value example
     */

    //    localhost:8086/helloClient
    @Bean
    public Supplier<Flux<String>> helloClient() {
        return () ->
            webClient
                .get()
                .uri("/hello")
                .retrieve()
                .bodyToFlux(String.class)
                .map(value -> value.concat(" - from client"))
                .doOnError(System.out::println)
            ;

    }

    //    localhost:8086/helloBuilder
    @Bean
    public Supplier<Flux<String>> helloBuilder() {
        return () ->
            webClientBuilder
                .baseUrl("http://gateway/serverless-example")
                .build()
                .get()
                .uri("/hello")
                .retrieve()
                .bodyToFlux(String.class)
                .map(value -> value.concat(" - from client"))
                .doOnError(System.out::println)
            ;

    }

    /**
     * flux example
     */

    //    localhost:8086/wordsClient
    @Bean
    public Supplier<Flux<String>> wordsClient() {
        return () ->
            webClient
                .get()
                .uri("/words")
                .retrieve()
                .bodyToFlux(String.class)
                .map(value -> value.concat(" - from client"))
                .doOnError(System.out::println)
            ;

    }

    //    function composition in Upper level
    //    localhost:8086/wordsClient,uppercase
    @Bean
    public Function<Flux<String>, Flux<String>> uppercase() {
        return flux -> flux.map(String::toUpperCase);
    }

    /**
     * profile example
     */

    //    localhost:8086/profilesClient
    @Bean
    public Supplier<Flux<CustomerProfile>> profilesClient() {
        return () ->
            webClient
                .get()
                .uri("/profiles")
                .retrieve()
                .bodyToFlux(CustomerProfile.class)
                .doOnError(System.out::println)
            ;

    }

    //    function composition in Lower level
    //    localhost:8086/fullProfilesClient
    @Bean
    public Supplier<Flux<CustomerProfile>> fullProfilesClient() {
        return () ->
            webClient
                .get()
                .uri("/profiles,withName,withMiles,withPassport")
                .retrieve()
                .bodyToFlux(CustomerProfile.class)
                .doOnError(System.out::println)
            ;

    }

}
