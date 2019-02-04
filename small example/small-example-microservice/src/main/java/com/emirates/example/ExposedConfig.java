package com.emirates.example;

import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;

/**
 * @author S750976
 */
@RefreshScope
@Configuration
public class ExposedConfig {

    @Value("${info.foo}")
    private String fooProperty;

    private String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    /**
     * value example
     */

    @Bean
    public Supplier<String> config() {
        return () -> fooProperty;
    }

    @Bean
    public Supplier<String> hello() {
        return () -> "hello";
    }

    @Bean
    public Function<String, String> uppercase() {
        return String::toUpperCase;
    }

    /**
     * flux example
     */

    @Bean
    public Supplier<Flux<String>> words() {
        return () -> Flux.fromArray(new String[] { "foo", "bar" });
    }

    @Bean
    public Function<Flux<String>, Flux<String>> lowercase() {
        return flux -> flux.map(String::toLowerCase);
    }

    /**
     * profile example
     */

    @Bean
    public Supplier<Flux<CustomerProfile>> profiles() {
        return () -> Flux.just(
            new CustomerProfile("1"),
            new CustomerProfile("2"),
            new CustomerProfile("3")
        );
    }

    @Bean
    public Function<Flux<CustomerProfile>, Flux<CustomerProfile>> withMiles() {
        return customerProfile -> customerProfile.map(
            value -> {
                value.miles = String.valueOf(ThreadLocalRandom.current().nextInt(100, 5000));
                return value;
            }
        );
    }

    @Bean
    public Function<Flux<CustomerProfile>, Flux<CustomerProfile>> withName() {
        return customerProfile -> customerProfile.map(
            value -> {
                StringBuilder generatedString = new StringBuilder();
                Stream.iterate(1, n -> n + 1)
                    .limit(10)
                    .forEach(s -> generatedString.append(alphabet.charAt(ThreadLocalRandom.current().nextInt(9))))
                ;
                value.name = generatedString.toString();
                return value;
            }
        );
    }

    @Bean
    public Function<Flux<CustomerProfile>, Flux<CustomerProfile>> withPassport() {
        return customerProfile -> customerProfile.map(
            value -> {
                value.passport = "passport sensitive id"; // remember GDPR
                return value;
            }
        );
    }

}
