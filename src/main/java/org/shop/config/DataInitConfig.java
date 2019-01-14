package org.shop.config;

import org.shop.*;
import org.shop.api.ProductService;
import org.shop.api.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitConfig {

    @Bean(initMethod="initData")
    public DataInitializer getDataInitializer(){
        return new DataInitializer();
    }

    @Bean
    public ProductInitializer getProductInitializer(ProductService productService){
        return new ProductInitializer(productService);
    }

    @Bean
    public UserInitializer getUserInitializer(UserService userService){
        return new UserInitializer(userService);
    }
}
