package it.example.services.organization.repository;

import java.util.List;

import it.example.services.organization.domain.Organization;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@ApplicationScoped
public class OrganizationPersistenceRepository {

	// ======================================
	// =             Injection              =
	// ======================================

	@Inject
	EntityManager entityManager;

	// ======================================
	// =              Methods               =
	// ======================================

	@Transactional
	public Organization findById(final Long id) {
		return entityManager.find(Organization.class, id);
	}

	@Transactional
	public List<Organization> findAll() {
		return entityManager.createQuery("SELECT o FROM Organization o", Organization.class).getResultList();
	}

	@Transactional
	public Organization add(Organization organization) {
		entityManager.persist(organization);
		return organization;
	}

	@Transactional
	public Organization update(Organization organization) {
		return entityManager.merge(organization);
	}

}

