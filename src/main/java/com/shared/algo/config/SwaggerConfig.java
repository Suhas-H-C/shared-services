package com.shared.algo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public final class SwaggerConfig {

	@Bean
	public OpenAPI api() {
		return new OpenAPI().info(new Info().title("Shared-Algo-Application").version("0.0.1SNAPSHOT")
				.description("This is the API documentation for shared-algorithms application"));
	}
}
