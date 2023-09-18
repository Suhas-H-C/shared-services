package com.shared.algo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Clock;
import java.time.ZoneId;

@Configuration
public class SharedServiceApplicationBeanConfiguration {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
    @Bean
    public Clock sharedServiceClock(){
        return Clock.system(ZoneId.of("UTC"));
    }
}
