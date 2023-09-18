package com.shared.algo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI api() {
        return new OpenAPI().info(new Info().title("Shared-Services-Application").version("0.0.1SNAPSHOT")
                .description("This is the API documentation for shared-services application"));
    }
}
