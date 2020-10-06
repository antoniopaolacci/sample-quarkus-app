package it.sample.services.department.repository;

import it.sample.services.department.domain.Department;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@ApplicationScoped
public class DepartmentRepository {

    // ======================================
    // =             Injection              =
    // ======================================

    @Inject
    EntityManager entityManager;

    // ======================================
    // =              Methods               =
    // ======================================

    @Transactional
    public Department findById(final Long id) {
        return entityManager.find(Department.class, id);
    }

    @Transactional
    public List<Department> findAll() {
        return entityManager.createQuery("SELECT m FROM Department m", Department.class).getResultList();
    }

    @Transactional
    public Department add(Department d) {
        entityManager.persist(d);
        return d;
    }

    @Transactional
    public Department update(Department d) {
        return entityManager.merge(d);
    }

    @Transactional
    public List<Department> findByOrganization(Long organizationId) {
    	return  entityManager.createQuery("SELECT m FROM Department m WHERE m.organizationId = :id", Department.class)
                .setParameter("id", organizationId)
                .getResultList();
    }
    
}//end class