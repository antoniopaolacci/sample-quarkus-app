package it.example.services.organization.repository;

import it.example.services.organization.domain.Organization;

import java.util.*;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class OrganizationRepository {

	private Set<Organization> organizations = new HashSet<>();

	public OrganizationRepository() {
		add(new Organization("OrganizationName 1", "Address 1"));
		add(new Organization("OrganizationName 2", "Address 2"));
	}

	public Organization add(Organization organization) {
		
		organization.setId((long) (organizations.size()+1));
		organizations.add(organization);
		
		return organization;
	}
	
	public Organization findById(Long id) {
		
		Optional<Organization> organization = organizations.stream().filter(a -> a.getId().equals(id)).findFirst();
		
		if (organization.isPresent())
			return organization.get();
		else
			return null;
	}

	public Set<Organization> findAll() {
		return organizations;
	}
	
}