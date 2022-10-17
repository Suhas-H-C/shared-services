package com.shared.algo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.shared.algo.config.SharedAlgoApplicationContext;

@SpringBootApplication
public class SharedAlgoApplication implements CommandLineRunner{

	
	private static final Logger LOGGER = LoggerFactory.getLogger(SharedAlgoApplication.class);
	
	@Autowired
	private ApplicationContext applicationContext;
	
	public static void main(String[] args) {
		SpringApplication.run(SharedAlgoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		SharedAlgoApplicationContext sharedAlgoApplicationConfig = applicationContext.getBean(SharedAlgoApplicationContext.class);
		LOGGER.info("{}",sharedAlgoApplicationConfig.getApplicationId());
	}

}
