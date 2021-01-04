package com.restassured.stepdef;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class Assignment3StepDef {

	private Response response;
	private String token;

	@Given("^I am an authorized user$")
	public void i_am_an_authorized_user() throws Throwable {
		String tokenUrl = RestAssured.baseURI = "https://bookstore.toolsqa.com/Account/v1/GenerateToken";
		response = RestAssured.given().header("Content-Type", "application/json").when()
				.body("{ \"userName\": \"Lakshmikanth\", \"password\": \"Restassured@123\"}").post(tokenUrl).then().extract()
				.response();
		String status = response.getBody().jsonPath().getString("status");
		Assert.assertEquals("Success", status);
		token = response.getBody().jsonPath().getString("token");
		System.out.println("Token: "+token);
	}

	@Given("^a book exists with an isbn of \"([^\"]*)\"$")
	public void a_book_exists_with_an_isbn_of(String isbn) throws Throwable {
		String getBookByIsbnurl = RestAssured.baseURI = "https://bookstore.toolsqa.com/BookStore/v1/Book?ISBN=" + isbn;
		response = RestAssured.get(getBookByIsbnurl).then().extract().response();
	}

	@When("^a user retrieves the book by isbn$")
	public void a_user_retrieves_the_book_by_isbn() throws Throwable {
		String resBody = response.getBody().asString();
		System.out.println(resBody);
	}

	@Then("^the status code is (\\d+)$")
	public void the_status_code_is(int code) throws Throwable {
		int actualCode = response.getStatusCode();
		Assert.assertEquals(code, actualCode);
	}

	@Then("^response includes the following in any order$")
	public void response_includes_the_following_in_any_order(DataTable data) throws Throwable {
		Map<String, String> details = data.asMap(String.class, String.class);
		String actualPages = response.getBody().jsonPath().getString("pages");
		String actualPublisher = response.getBody().jsonPath().getString("publisher");
		String actualTitle = response.getBody().jsonPath().getString("title");
		String expectedPages = details.get("pages");
		String expectedPublisher = details.get("publisher");
		String expectedTitle = details.get("title");
		Assert.assertEquals(actualPages, expectedPages);
		Assert.assertEquals(actualPublisher, expectedPublisher);
		Assert.assertEquals(actualTitle, expectedTitle);
	}

	@Given("^A list of books are available$")
	public void a_list_of_books_are_available() throws Throwable {
		String getBooksUrl = RestAssured.baseURI = "https://bookstore.toolsqa.com/BookStore/v1/Books";
		response = RestAssured.get(getBooksUrl).then().extract().response();
		String resBody = response.getBody().asString();
		System.out.println(resBody);
	}

	@When("^I add a book to my reading list$")
	public void i_add_a_book_to_my_reading_list() throws Throwable {
		Map<String, String> headers = new HashMap<>();
		headers.put("Content-Type", "application/json");
		headers.put("Authorization", "Bearer "+token);
		String addBookToReadListurl = RestAssured.baseURI = "https://bookstore.toolsqa.com/BookStore/v1/Books";
		response = RestAssured.given().headers(headers).when()
		.body("{ \"userId\": \"eae7b590-3195-4509-8755-f2bc7a5312db\", \"collectionOfIsbns\": [ { \"isbn\": \"9781449325862\" } ]}")
		.post(addBookToReadListurl).then().extract().response();
	}

	@Then("^The book is added$")
	public void the_book_is_added() throws Throwable {
		System.out.println(response.getBody().asString());
		int statusCode = response.getStatusCode();
		System.out.println(statusCode);
		Assert.assertEquals(201, statusCode);
	}

	@When("^I remove a book from my reading list$")
	public void i_remove_a_book_from_my_reading_list() throws Throwable {
		String deleteBookInReadListurl = "https://bookstore.toolsqa.com/BookStore/v1/Books?UserId=eae7b590-3195-4509-8755-f2bc7a5312db";
		response = RestAssured.given().header("Authorization", "Bearer "+token).delete(deleteBookInReadListurl).then().extract().response();
	}

	@Then("^The book is removed$")
	public void the_book_is_removed() throws Throwable {
		System.out.println(response.getBody().asString());
		System.out.println(response.getStatusCode());
	}
}
