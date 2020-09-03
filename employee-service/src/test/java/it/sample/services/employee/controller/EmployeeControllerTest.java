package it.sample.services.employee.controller;

import java.util.UUID;

import io.quarkus.test.junit.QuarkusTest;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

/*
 * These tests use RestAssured, but feel free to use your favorite library.
 * You can run these using Maven.
 */

@QuarkusTest
public class EmployeeControllerTest {

    @Test
    public void testHelloEndpoint() {
        given()
          .when().get("/employees")
          .then()
             .statusCode(200)
             .body(is("hello"));
    }
    
    @Test
    public void testGreetingEndpoint() {
        
    	String uuid = UUID.randomUUID().toString();
        
        given()
          .pathParam("id", uuid)
          .when().get("/employees/{id}")
          .then()
            .statusCode(200)
            .body(is("hello " + uuid));
    } 

}