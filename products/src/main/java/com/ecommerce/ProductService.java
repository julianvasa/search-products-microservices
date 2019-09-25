package com.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.netflix.ribbon.RibbonClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableEurekaClient
public class ProductService {

	public static void main(String[] args) {
		// Tell server to look for product-server.yml
		System.setProperty("spring.config.name", "product-server");
		SpringApplication.run(ProductService.class, args);
	}
}
