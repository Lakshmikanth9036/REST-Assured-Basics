package com.restassured.stepdef;

import java.util.List;
import java.util.Map;

import com.restassured.utility.Utility;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class Assignment1StepDef {

	private Response response;
	private String url;

	@Given("^I have an endpoint url \"([^\"]*)\"$")
	public void i_have_an_endpoint_url_something(String baseUrl) throws Throwable {
		url = RestAssured.baseURI = baseUrl;
	}

	@When("^I hit the get request$")
	public void i_hit_the_get_request() throws Throwable {
		response = RestAssured.get(url).then().extract().response();
	}

	@Then("^I should validate the response code and response body$")
	public void i_should_validate_the_response_code_and_response_body() throws Throwable {
		Utility.succesStatusCheck(response);
	}

	@When("^I hit the post request$")
	public void i_hit_the_post_request() throws Throwable {
		response = RestAssured.given().header("Content-Type", "application/json").when()
				.body("{\"name\": \"kanth\", \"salary\": \"25000\" , \"age\": \"23\"}").post(url).then().extract()
				.response();
	}

	@Then("^I should validate the response code and success message after Post request$")
	public void i_should_validate_the_response_code_and_success_message_after_Post_request() throws Throwable {
		Utility.succesStatusCheck(response);
	}

	@When("^I hit the request and update the user name$")
	public void i_hit_the_request_and_update_the_user_name() throws Throwable {
		response = RestAssured.given().header("Content-Type", "application/json").when().body("{\"name\": \"kanth\"}")
				.put(url).then().extract().response();
	}

	@Then("^I should validate the response code and success message after Put request$")
	public void i_should_validate_the_response_code_and_success_message_after_Put_request() throws Throwable {
		Utility.succesStatusCheck(response);
	}
	
	@Then("^I should validate the response code and print all the employee names from the data set$")
	public void i_should_validate_the_response_code_and_print_all_the_employee_names_from_the_data_set() throws Throwable {
		String resBody = Utility.succesStatusCheck(response);
		List<String> usernames = Utility.getListFromJsonPath(resBody, "$..employee_name");
		usernames.forEach(System.out::println);
	}

	@Then("^I should validate employee salary associated with employee name from data set$")
	public void i_should_validate_employee_salary_associated_with_employee_name_from_data_set() throws Throwable {
		String resBody = Utility.succesStatusCheck(response);
		List<String> usersalary = Utility.getListFromJsonPath(resBody, "$..employee_salary");
		usersalary.forEach(System.out::println);
		List<Map<String,String>> datalist = response.getBody().jsonPath().getList("data");
		datalist.forEach(data -> {
			System.out.println("Employee name: "+data.get("employee_name")+ " Salary: "+data.get("employee_salary"));
		});
	}

	@Then("^I should validate all the headers from the response$")
	public void i_should_validate_all_the_headers_from_the_response() throws Throwable {
		Headers headers = response.getHeaders();
		headers.forEach(header -> {
			System.out.println("Key: "+header.getName()+"\t Value: "+header.getValue());
		});
	}
	
	@When("^I hit the request for \"([^\"]*)\"$")
	public void i_hit_the_request_for(String page) throws Throwable {
		url = url+"?page="+page;
		response = RestAssured.get(url).then().extract().response();
		
	}

	@Then("^I should validate the response code and print all the email ids from the data set$")
	public void i_should_validate_the_response_code_and_print_all_the_email_ids_from_the_data_set() throws Throwable {
		String resBody = Utility.succesStatusCheck(response);
		List<String> emails = Utility.getListFromJsonPath(resBody, "$..email");
		emails.forEach(System.out::println);
	}

	@Then("^I should validate last name associated with email id from data set$")
	public void i_should_validate_last_name_associated_with_email_id_from_data_set() throws Throwable {
		List<Map<String, String>> dataList = response.getBody().jsonPath().getList("data");
		dataList.forEach(user -> {
			System.out.println("Lastname: "+user.get("last_name")+ "\t Email: "+user.get("email"));
		});
	}

	@Then("^I should validate all the users headers from the response$")
	public void i_should_validate_all_the_users_headers_from_the_response() throws Throwable {
		Headers headers = response.getHeaders();
		headers.forEach(header -> {
			System.out.println("Key: "+header.getName()+"\t Value: "+header.getValue());
		});
	}
}
