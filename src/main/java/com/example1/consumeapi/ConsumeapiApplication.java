package com.example1.consumeapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication

//@EntityScan("com.example1.entity")
//@EnableJpaRepositories("com.example1.repository.ProductRepository")
public class ConsumeapiApplication {

	@Bean
	public RestTemplate getRestTemplate(){
		return new RestTemplate();
	}
	public static void main(String[] args) {
		SpringApplication.run(ConsumeapiApplication.class, args);
	}




}
