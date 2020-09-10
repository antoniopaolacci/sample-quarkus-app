package it.sample.services.employee.mock;

import io.quarkus.test.Mock;
import it.sample.services.employee.domain.Organization;
import it.sample.services.employee.rest.client.OrganizationRestClient;

import javax.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.rest.client.inject.RestClient;

@Mock
@ApplicationScoped
@RestClient
public class MockOrganizationClient implements OrganizationRestClient {

	@Override
	public Organization getOrganizationById(Long organizationId) {
		return new Organization("OrganizationName 1", "Address 1");
	}



}


