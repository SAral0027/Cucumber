package com.stepdefinition;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import com.base.BaseClass;

import io.cucumber.java.en.*;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GITRequests extends BaseClass {
	RequestSpecification reqSpec;
	Response response;

	@When("User make GET request for user info with {string}")
	public void user_make_get_request_for_user_info_with(String endpoint) {
		reqSpec = initializeRestAssured();
		response = requestType("GET", endpoint);
	}

	@Then("User should verify statuscode {int} and response body {string} value")
	public void user_should_verify_statuscode_and_response_body_value(int code, String login) {
		int statusCode = getStatusCode(response);
		Assert.assertEquals(code, statusCode);
		Assert.assertEquals(login, response.jsonPath().get("login"));
	}

	@When("User make GET request for repo info with {string}")
	public void user_make_get_request_for_repo_info_with(String endpoint) {
		reqSpec = initializeRestAssured();
		response = requestType("GET", endpoint);
	}

	@Then("User should verify statuscode {int} and response body contains {string} value")
	public void user_should_verify_statuscode_and_response_body_contains_value(int code, String name) {
		int statusCode = getStatusCode(response);
		Assert.assertEquals(code, statusCode);
		Assert.assertEquals(name, response.jsonPath().get("[0].name"));
	}

	@Given("Add the required headers and create body")
	public void add_the_required_headers_and_create_body() throws IOException {
		reqSpec = initializeRestAssured();
		List<Header> listHeaders = new ArrayList<Header>();
		Header h1 = new Header("Authorization", "Bearer " + getPropertyFileValue("token"));
		Header h2 = new Header("Content-Type", "application/json");
		listHeaders.add(h1);
		listHeaders.add(h2);
		Headers headers = new Headers(listHeaders);
		addHeaders(headers);
		addBody(new File(getPropertyFileValue("jsonPathOfCreateRepo")));
	}

	@When("User make POST request to create new repo with {string}")
	public void user_make_post_request_to_create_new_repo_with(String endpoint) {
		response = requestType("POST", endpoint);
	}

	@Then("User should verify response body {string} and statuscode {int}")
	public void user_should_verify_response_body_and_statuscode(String name, int code) {
		int statusCode = getStatusCode(response);
		Assert.assertEquals(code, statusCode);
		Assert.assertEquals(name, response.jsonPath().get("name"));
	}

	@Given("Add the required headers and update body")
	public void add_the_required_headers_and_update_body() throws IOException {
		reqSpec = initializeRestAssured();
		List<Header> listHeaders = new ArrayList<Header>();
		Header h1 = new Header("Authorization", "Bearer " + getPropertyFileValue("token"));
		Header h2 = new Header("Content-Type", "application/json");
		listHeaders.add(h1);
		listHeaders.add(h2);
		Headers headers = new Headers(listHeaders);
		addHeaders(headers);
		addBody(new File(getPropertyFileValue("jsonPathOfUpdateRepo")));
	}

	@When("User make PATCH request to update existing repo with {string}")
	public void user_make_patch_request_to_update_existing_repo_with(String endpoint) {
		response = requestType("PATCH", endpoint);
	}

	@Then("User should verify response body conatins {string} and statuscode {int}")
	public void user_should_verify_response_body_conatins_and_statuscode(String name, int code) {
		int statusCode = getStatusCode(response);
		Assert.assertEquals(code, statusCode);
		Assert.assertEquals(name, response.jsonPath().getString("name"));
	}

	@Given("Add the required headers")
	public void add_the_required_headers() throws IOException {
		reqSpec = initializeRestAssured();
		addHeader("Authorization", "Bearer " + getPropertyFileValue("token"));
	}

	@When("User make DELETE request to delete existing repo with {string}")
	public void user_make_delete_request_to_delete_existing_repo_with(String endpoint) {
		response = requestType("DELETE", endpoint);
	}

	@Then("User should verify statuscode {int}")
	public void user_should_verify_statuscode(int code) {
		int statusCode = getStatusCode(response);
		Assert.assertEquals(code, statusCode);
	}

}
