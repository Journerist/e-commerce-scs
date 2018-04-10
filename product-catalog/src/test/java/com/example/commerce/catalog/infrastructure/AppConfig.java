package com.example.commerce.catalog.infrastructure;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.example.commerce.catalog.core.application.ProductApplicationService;
import com.example.commerce.catalog.core.domain.entity.customer.CustomerFactory;
import com.example.commerce.catalog.core.infrastructure.config.WebMvcConfig;
import com.example.commerce.catalog.core.infrastructure.db.SequenceGenerator;
import com.example.commerce.catalog.core.interfaces.controller.GreetingController;
import com.example.commerce.catalog.sample.data.SampleProductData;

@Configuration
@ComponentScan(basePackageClasses = { WebMvcConfig.class, GreetingController.class, ProductApplicationService.class,
        SampleProductData.class, SequenceGenerator.class, CustomerFactory.class })
public class AppConfig {

}
