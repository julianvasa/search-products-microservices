package com.epages;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SearchService {

	public static void main(String[] args) {
		// Tell server to look for search-server.yml
		System.setProperty("spring.config.name", "search-server");
		SpringApplication.run(SearchService.class, args);
	}
}
