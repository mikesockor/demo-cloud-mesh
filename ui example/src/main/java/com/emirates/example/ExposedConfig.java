package com.emirates.example;

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
@RibbonClients({ @RibbonClient(name = "airports") })
public class ExposedConfig {

    @Autowired
    private WebClient.Builder webClientBuilder;

    /**
     * value example
     */

    @Bean
    public Supplier<Flux<Airport>> hello() {
        return () ->
            webClientBuilder
            //            .baseUrl("http://gateway/serverless-example")
            .baseUrl("http://gateway/airports/allairports")
            .build()
//            webClient
                .get()
//                .uri("/hello")
                .retrieve()
                .bodyToFlux(Airport.class)
            .doOnError(System.out::println)
            ;
    }

//    @Bean
//    public Supplier<String> config() {
//        return () -> fooProperty;
//    }


    //    /**
//     * flux example
//     */
//
//    @Bean
//    public Supplier<Flux<String>> words() {
//        return () -> Flux.fromArray(new String[] { "foo", "bar" });
//    }
//
//    @Bean
//    public Function<Flux<String>, Flux<String>> lowercase() {
//        return flux -> flux.map(String::toLowerCase);
//    }
//
//    /**
//     * profile example
//     */
//
//    @Bean
//    public Supplier<Flux<CustomerProfile>> profiles() {
//        return () -> Flux.just(
//            new CustomerProfile("1"),
//            new CustomerProfile("2"),
//            new CustomerProfile("3")
//        );
//    }
//
//    @Bean
//    public Function<Flux<CustomerProfile>, Flux<CustomerProfile>> withMiles() {
//        return customerProfile -> customerProfile.map(
//            value -> {
//                value.miles = String.valueOf(ThreadLocalRandom.current().nextInt(100, 5000));
//                return value;
//            }
//        );
//    }
//
//    @Bean(name = "stupidName/sdf")
//    public Function<Flux<CustomerProfile>, Flux<CustomerProfile>> withName() {
//        return customerProfile -> customerProfile.map(
//            value -> {
//                StringBuilder generatedString = new StringBuilder();
//                Stream.iterate(1, n -> n + 1)
//                    .limit(10)
//                    .forEach(s -> generatedString.append(alphabet.charAt(ThreadLocalRandom.current().nextInt(9))))
//                ;
//                value.name = generatedString.toString();
//                return value;
//            }
//        );
//    }
//
//    @Bean
//    public Function<Flux<CustomerProfile>, Flux<CustomerProfile>> withPassport() {
//        return customerProfile -> customerProfile.map(
//            value -> {
//                value.passport = "passport sensitive id"; // remember GDPR
//                return value;
//            }
//        );
//    }

}
