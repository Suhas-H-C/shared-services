package com.shared.info.cucumber;

import com.shared.info.SharedServicesApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import io.cucumber.spring.CucumberContextConfiguration;

@SpringBootTest(classes = SharedServicesApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
@CucumberContextConfiguration
class CucumberSpringBootConfigs {

}
