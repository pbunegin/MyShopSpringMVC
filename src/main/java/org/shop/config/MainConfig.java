package org.shop.config;

import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@Import({DataInitConfig.class,FactoryConfig.class,RepositoryConfig.class,ServiceConfig.class})
@PropertySource("classpath:orderRepository.properties")
@EnableAspectJAutoProxy
public class MainConfig {
    @Bean
    public static PropertySourcesPlaceholderConfigurer configurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
