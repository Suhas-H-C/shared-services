package com.shared.algo.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public final class CredentialEncoder {

	@Bean
	public PasswordEncoder myEncoder() {
		return new BCryptPasswordEncoder();
	}
}
