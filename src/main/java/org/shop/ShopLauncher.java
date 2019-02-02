package org.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
//@EnableJpaAuditing
public class ShopLauncher {

	public static void main(String[] args) {
		SpringApplication.run(ShopLauncher.class, args);
	}

}