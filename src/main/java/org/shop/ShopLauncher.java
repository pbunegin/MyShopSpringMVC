package org.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
//@EnableJpaAuditing
public class ShopLauncher extends SpringBootServletInitializer {
	
	public static void main(String[] args) {
		SpringApplication.run(ShopLauncher.class, args);
	}

}