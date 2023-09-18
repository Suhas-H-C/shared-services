package com.shared.info.cucumber;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features", glue = {
		"com.shared.cucumber" }, tags = "~@fields,@health,@content,@jsonFetch", monochrome = true)
class CucumberIntegration {

}
