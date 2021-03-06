package it.example.services.organization.controller;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class OrganizationControllerTest {

    @Test
    public void testHelloEndpoint() {
        given()
          .when().get("/organizations/hello")
          .then()
             .statusCode(200)
             .body(is("hello"));
    }

	@Test
	public void testOrganizations() {

		given()
		.when().get("/organizations")
		.then()
		.statusCode(200)
		.assertThat().body("size()", is(2));

	}
    
}