package it.sample.services.department.controller;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class DepartmentControllerTest {

	@Test
	public void testHelloEndpoint() {
		given()
		.when().get("/departments/hello")
		.then()
		.statusCode(200)
		.body(is("hello"));
	}

	@Test
	public void testDepartments() {

		given()
		.when().get("/departments")
		.then()
		.statusCode(200)
		.assertThat().body("size()", is(2));

	}

}