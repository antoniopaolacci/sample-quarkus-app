package it.sample.services.employee.storage;

import it.sample.services.employee.domain.Employee;

import java.util.List;
import java.util.Optional;

public interface StorageService {

	Optional<Employee> add(Employee e);

	Optional<Employee> update(Employee e);

	Optional<Employee> delete(Employee e);

	Optional<Employee> get(Employee e);

	List<Employee> getAll();
	
	Optional<Employee> findById(Long id);

	long count();
	
	boolean isReady();

}