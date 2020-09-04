package it.sample.services.employee.controller;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import it.sample.services.employee.domain.Employee;

import org.junit.jupiter.api.Test;

/*
 * These tests use RestAssured, but feel free to use your favorite library.
 * You can run these using Maven.
 */

@QuarkusTest
public class EmployeeControllerTest {
	
    @Test
    public void testVersion() {

    	String version = "1.1.2";

    	given()
    	.when().get("/employees/version")
    	.then()
    	.statusCode(200)
    	.body(is("Microservice version=" + version));
    	
    }

	@Test
	public void testEmployees() {

		given()
		.when().get("/employees")
		.then()
		.statusCode(200)
		.assertThat().body("size()", is(2));
		
	}

	@Test
	public void testEmployeeById() {

		String uuid = "1";
		// Expected: {"id":1,"organizationId":1,"departmentId":1,"name":"John Smith","age":30,"position":"Developer"}
		
		given()
		.pathParam("id", uuid)
		.when().get("/employees/{id}")
		.then()
		.statusCode(200)
		.assertThat()
	    .body("name", equalTo("John Smith")); 
	}

	@Test
	public void testAddEmployee() {

		Employee emp = new Employee(1L, 1L, "Joe Fake", 34, "Consultant");

		given()
		.body(emp).contentType(ContentType.JSON)
		.post("/employees")
		.then()
		.statusCode(200);
	}

}