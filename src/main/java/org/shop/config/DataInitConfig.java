package org.shop.config;

import org.shop.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitConfig {

    @Bean(initMethod="initData")
    public DataInitializer getDataInitializer(){
        return new DataInitializer();
    }

    @Bean
    public ProductInitializer getProductInitializer(){
        return new ProductInitializer();
    }

    @Bean
    public UserInitializer getUserInitializer(){
        return new UserInitializer();
    }
}
