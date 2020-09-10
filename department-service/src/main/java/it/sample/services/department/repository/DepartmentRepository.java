package it.sample.services.department.repository;

import it.sample.services.department.domain.Department;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DepartmentRepository {

	private Set<Department> departments = new HashSet<>();

    public DepartmentRepository() {
        add(new Department(1L, "Test DepartmentName One"));
        add(new Department(2L, "Test DepartmentName Two"));
        add(new Department(2L, "Test DepartmentName Three"));
    }

    public Department add(Department department) {
        department.setId((long) (departments.size() + 1));
        departments.add(department);
        return department;
    }

    public Department findById(Long id) {
        
    	Optional<Department> department = departments.stream().filter(a -> a.getId().equals(id)).findFirst();
        
    	if (department.isPresent())
            return department.get();
        else
            return null;
    }

    public Set<Department> findAll() {
        return departments;
    }

    public Set<Department> findByOrganization(Long organizationId) {
        return departments.stream().filter(a -> a.getOrganizationId().equals(organizationId)).collect(Collectors.toSet());
    }

}//end class