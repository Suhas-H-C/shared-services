package com.shared.algo;

import com.shared.algo.config.SharedServicesApplicationContext;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@Slf4j
@AllArgsConstructor
@SpringBootApplication
public class SharedServicesApplication implements CommandLineRunner {

    private final ApplicationContext applicationContext;

    public static void main(String[] args) {
        SpringApplication.run(SharedServicesApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        SharedServicesApplicationContext sharedAlgoApplicationConfig = applicationContext
                .getBean(SharedServicesApplicationContext.class);
        log.info("{}", sharedAlgoApplicationConfig.getApplicationId());
    }
}
