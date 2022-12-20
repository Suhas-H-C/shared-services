package com.shared.cucumber;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class FieldsCheckStepDefs {

	@LocalServerPort
	String port;

	ResponseEntity<?> lastResponse;

	@When("user makes API request {string}")
	public void makeRequest(String url) {
		lastResponse = new RestTemplate().exchange("http://localhost:" + port + url, HttpMethod.GET, null,
				Object.class);
	}

	@Then("response is good")
	public void checkStatus() {
		assertEquals(HttpStatus.OK, lastResponse.getStatusCode());
	}
}
