package com.restassured;


import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/main/resources/features/",
		glue = "com.restassured.stepdef", 
		tags = {"@smoke" },
		plugin = { 
				"pretty",
				"html:target/cucumber_report",
				"json:target/cucumber.json"
				},
		monochrome = true
		)
public class AppTest{
	
}