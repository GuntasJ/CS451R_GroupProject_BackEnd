package com.tags.cs451r_groupproject_backend.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;
import java.util.function.IntFunction;

@Configuration
public class LoadDatabase {

    private static final Logger logger = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    public CommandLineRunner initDatabase(ApplicationRepository applicationRepository) {
        return args -> {
            logger.info("Preloading " + applicationRepository.save(new Application("John", "Smith")));
            logger.info("Preloading " + applicationRepository.save(new Application("Jane", "Doe")));
        };
    }

}
