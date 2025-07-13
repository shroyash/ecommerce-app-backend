package com.shroyash.multi_shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.shroyash.multi_shop.repository")
public class MultiShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(MultiShopApplication.class, args);
	}

}
