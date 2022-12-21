package com.shared.algo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.shared.algo.config.SharedServicesApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class SharedServicesApplication implements CommandLineRunner {

	private static final Logger LOGGER = LoggerFactory.getLogger(SharedServicesApplication.class);

	@Autowired
	private ApplicationContext applicationContext;

	public static void main(String[] args) {
		SpringApplication.run(SharedServicesApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		SharedServicesApplicationContext sharedAlgoApplicationConfig = applicationContext
				.getBean(SharedServicesApplicationContext.class);
		LOGGER.info("{}", sharedAlgoApplicationConfig.getApplicationId());
	}

	@Bean
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}

}
