package org.shop.config;

import org.shop.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class ConfigApp implements WebMvcConfigurer {



    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .excludePathPatterns("/css/*", "/login", "/error", "/registration");
    }

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
