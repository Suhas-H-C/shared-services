package com.shared.cucumber;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.shared.algo.SharedServicesApplication;

import io.cucumber.spring.CucumberContextConfiguration;

@SpringBootTest(classes = SharedServicesApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
@CucumberContextConfiguration
class CucumberSpringBootConfigs {

}
