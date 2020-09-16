package it.sample.services.employee.repository;

import javax.enterprise.context.ApplicationScoped;

import it.sample.services.employee.domain.Employee;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@ApplicationScoped
public class EmployeeRepository {

    private Set<Employee> employees = new HashSet<>();

    public EmployeeRepository() {
    	// initialize some data
        this.add(new Employee("John Smith", 30, "Developer", 1L, 1L));
        this.add(new Employee("Paul Walker", 40, "Architect", 2L, 1L));
        this.add(new Employee("John Snow", 35, "Consultant", 1L, 3L));
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