package it.sample.services.employee.controller;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import it.sample.services.employee.domain.Employee;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * These tests use RestAssured, but feel free to use your favorite library.
 * You can run these using Maven.
 */

@QuarkusTest
public class EmployeeControllerTest {
	
	private final Logger log = LoggerFactory.getLogger(EmployeeControllerTest.class);
	
    @Test
    public void testVersion() {

    	log.debug("----- INIT TEST testVersion() -----");
    	
    	given()
    	.when().get("/employees/version")
    	.then()
    	.statusCode(200)
    	.body(is("1.1.0"));
    	
    }

	@Test
	public void testEmployees() {
		
		log.debug("----- INIT TEST employees() -----");

		given()
		.when().get("/employees")
		.then()
		.statusCode(200)
		.assertThat().body("size()", is(3));
		
	}

	@Test
	public void testEmployeeById() {
		
		log.debug("----- INIT TEST testEmployeeById() -----");

		String uuid = "1";
		/*
		 *  {
		 *	  "id": 1,
		 *	  "organizationId": 1,
		 *	  "departmentId": 1,
		 *	  "name": "John Smith",
		 *	  "age": 30,
		 *	  "position": "Developer"
		 *	}
		 *
		 */
		
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

		log.debug("----- INIT TEST testAddEmployee() -----");
		
		Employee emp = new Employee("Joe Fake", 34, "Consultant", 1L, 1L);

		given()
		.body(emp).contentType(ContentType.JSON)
		.post("/employees")
		.then()
		.statusCode(200);
	}

}//end class