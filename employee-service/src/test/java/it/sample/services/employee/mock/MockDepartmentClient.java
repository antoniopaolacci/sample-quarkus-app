package it.sample.services.employee.mock;

import io.quarkus.test.Mock;
import it.sample.services.employee.domain.Department;
import it.sample.services.employee.rest.client.DepartmentRestClient;

import javax.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.rest.client.inject.RestClient;

@Mock
@ApplicationScoped
@RestClient
public class MockDepartmentClient implements DepartmentRestClient {

	@Override
	public Department getDepartmentById(Long departmentId) {
		return new Department(1L, "Test DepartmentName One");
	}

}


