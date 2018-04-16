package com.example.commerce.catalog.core.infrastructure;

import javax.annotation.PostConstruct;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.session.data.mongo.config.annotation.web.http.EnableMongoHttpSession;

import com.example.commerce.catalog.core.application.ProductApplicationService;
import com.example.commerce.catalog.core.domain.entity.customer.CustomerFactory;
import com.example.commerce.catalog.core.infrastructure.config.WebMvcConfig;
import com.example.commerce.catalog.core.infrastructure.db.SequenceGenerator;
import com.example.commerce.catalog.core.infrastructure.integration.IntegratedKafkaServer;
import com.example.commerce.catalog.core.interfaces.controller.GreetingController;
import com.example.commerce.catalog.sample.data.SampleProductData;

@SpringBootApplication
@ComponentScan(basePackageClasses = { WebMvcConfig.class, GreetingController.class, ProductApplicationService.class,
        SampleProductData.class, SequenceGenerator.class, CustomerFactory.class })
@EnableMongoRepositories("com.example.commerce.catalog.core.domain.entity.product")
@EnableMongoHttpSession
public class DemoApplication {

    @Autowired
    private Environment env;

    private static Logger log = LoggerFactory.getLogger(DemoApplication.class);

    @PostConstruct
    public void beforeStartup() {
        if (env.acceptsProfiles("dev")) {
            log.info("Dev profile is active, check if there is a running kafka server...");
            IntegratedKafkaServer.startIfNoServerIsRunning();
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mm = new ModelMapper();
        mm.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        return mm;
    }

}
