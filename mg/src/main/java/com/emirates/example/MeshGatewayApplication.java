package com.emirates.example;

/*-
 * #%L
 * configuration-server
 * %%
 * Copyright (C) 2018 Emirates Group IT
 * %%
 * This file is part of OCSL.
 *
 * OCSL can not be copied and/or distributed without the express permission of Emirates Group IT
 * #L%
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

/**
 * The type Config service application.
 */
@EnableDiscoveryClient
@EnableHystrix
@SpringBootApplication
public class MeshGatewayApplication {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(final String[] args) {
        SpringApplication.run(MeshGatewayApplication.class, args);
    }
}
