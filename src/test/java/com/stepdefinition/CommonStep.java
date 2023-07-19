package com.stepdefinition;

import static io.restassured.RestAssured.baseURI;
import java.io.IOException;
import com.base.BaseClass;
import io.cucumber.java.en.Given;

public class CommonStep extends BaseClass {

	@Given("Hit the baseURI")
	public void hit_the_base_uri() throws IOException {
		baseURI = getPropertyFileValue("baseURI");
	}
}
