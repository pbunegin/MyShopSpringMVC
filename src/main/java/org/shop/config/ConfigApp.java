package org.shop.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.sql.DataSource;

@Configuration
public class ConfigApp implements WebMvcConfigurer {
    @Autowired
    private DataSource dataSource;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .excludePathPatterns("/css/*", "/login", "/error", "/registration", "/js/*");
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setPackagesToScan("org.shop.data");
        return sessionFactory;
    }

//    @Bean(initMethod="initData")
//    public DataInitializer getDataInitializer(){
//        return new DataInitializer();
//    }
//
//    @Bean
//    public ProductInitializer getProductInitializer(){
//        return new ProductInitializer();
//    }
//
//    @Bean
//    public UserInitializer getUserInitializer(){
//        return new UserInitializer();
//    }
}
