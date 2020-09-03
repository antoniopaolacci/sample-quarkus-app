package it.sample.services.employee.repository;

import javax.enterprise.context.ApplicationScoped;

import it.sample.services.employee.domain.Employee;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/*
 * Dependency injection in Quarkus is based on ArC which is a CDI-based dependency injection solution tailored for Quarkus' architecture. 
 * You can learn more about it in the Contexts and Dependency Injection guide.
 * ArC comes as a dependency of quarkus-resteasy so you already have it handy.
 */

@ApplicationScoped
public class EmployeeRepository {

    private Set<Employee> employees = new HashSet<>();

    public EmployeeRepository() {

    	// initialize some data
        this.add(new Employee(1L, 1L, "John Smith", 30, "Developer"));
        this.add(new Employee(1L, 1L, "Paul Walker", 40, "Architect"));

    }

    public Employee add(Employee employee) {

        employee.setId((long) (employees.size()+1));

        employees.add(employee);

        return employee;

    }

    public Employee findById(Long id) {

        Optional<Employee> employee = employees.stream().filter(a -> a.getId().equals(id)).findFirst();

        if (employee.isPresent())

            return employee.get();

        else

            return null;

    }

    public Set<Employee> findAll() {

        return employees;

    }

    public Set<Employee> findByDepartment(Long departmentId) {

        return employees.stream().filter(a -> a.getDepartmentId().equals(departmentId)).collect(Collectors.toSet());

    }

    public Set<Employee> findByOrganization(Long organizationId) {

        return employees.stream().filter(a -> a.getOrganizationId().equals(organizationId)).collect(Collectors.toSet());

    }

}//end class