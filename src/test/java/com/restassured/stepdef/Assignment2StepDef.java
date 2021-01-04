package com.restassured.stepdef;

import com.restassured.utility.Utility;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class Assignment2StepDef {
	private Response response;
	private String url;
	
	@Given("^I have  \"([^\"]*)\"$")
	public void i_have(String baseUrl) throws Throwable {
	   url = RestAssured.baseURI = baseUrl;
	}

	@When("^get request$")
	public void get_request() throws Throwable {
		response = RestAssured.get(url).then().extract().response();
	}

	@Then("^validate the response code and response body$")
	public void validate_the_response_code_and_response_body() throws Throwable {
		Utility.succesStatusCheck(response);
	}
	
	@When("^post request$")
	public void post_request() throws Throwable {
		response = RestAssured.given().header("Content-Type", "application/json").when()
				.body("{\"name\": \"kanth\"}").post(url).then().extract()
				.response();
	}

	@Then("^I should validate the response code$")
	public void i_should_validate_the_response_code() throws Throwable {
		Utility.succesStatusCheck(response);
	}
	
}
