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
        List<String> pathPatterns = new ArrayList<>();
//        pathPatterns.add("/js/*");
        pathPatterns.add("/css/*");
//        pathPatterns.add("/img/*");
        pathPatterns.add("/login");
        pathPatterns.add("/error");
        pathPatterns.add("/registration");
        registry.addInterceptor(new LoginInterceptor()).excludePathPatterns(pathPatterns);
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
