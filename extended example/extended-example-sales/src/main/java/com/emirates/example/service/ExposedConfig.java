package com.emirates.example.service;

import com.emirates.example.model.Flight;
import com.emirates.example.model.Itinerary;
import java.util.function.Function;
import java.util.function.Supplier;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author S750976
 */
@RefreshScope
@Configuration
public class ExposedConfig {

    /**
     * value example
     */
    @Bean
    public Supplier<String> hello() {
        return () -> "hello";
    }

    @Bean
    public Function<Flight, Itinerary> price() {
        return SalesTicketingService::price;
    }

}
