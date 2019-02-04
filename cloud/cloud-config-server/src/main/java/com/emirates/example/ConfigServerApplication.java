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
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * The type Config service application.
 */

@EnableConfigServer
@EnableDiscoveryClient
@SpringBootApplication
public class ConfigServerApplication {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(ConfigServerApplication.class, args);
    }
}
