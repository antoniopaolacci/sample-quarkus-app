package it.sample.services.employee.storage;

import io.quarkus.runtime.annotations.RegisterForReflection;
import it.sample.services.employee.domain.Employee;
import it.sample.services.employee.persistence.PersistedEmployee;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;

@RegisterForReflection(fields = false, methods = true)
@ApplicationScoped
public class InMemoryStorageService implements StorageService {

	private List<PersistedEmployee> employees = new LinkedList<PersistedEmployee>();
	private long tableSize;

	public InMemoryStorageService(){
		employees.add(new PersistedEmployee(1, "John Smith", 30, "Developer", 1, 2));
		employees.add(new PersistedEmployee(2, "Paul Walker", 40, "Architect", 1, 2));
		employees.add(new PersistedEmployee(3, "John Snow", 30, "Consultant", 1, 2));
	}
	
	@Override
	public  Optional<Employee> add(Employee e) {
		
		PersistedEmployee pe = transformToPersist(e).get();
		// Simulate AUTO_INCREMENT 
		pe.setId(this.employees.size()+1);
		
		this.employees.add(pe);
		
		return transformToDomain(pe);
	}

	@Override
	public Optional<Employee> update(Employee e) {

		int index = 0;
		PersistedEmployee persisted = transformToPersist(e).get();
		
		for(PersistedEmployee o : this.employees){
			if(o.equals(persisted))
				break;
			else 
				index++;
		}
		
		if(index==this.employees.size())
			return Optional.empty();
		else {
			this.employees.set(index,persisted);
			return transformToDomain(persisted);
		}
		
	}

	@Override
	public Optional<Employee> delete(Employee e) {
		
		Optional<PersistedEmployee> pe = transformToPersist(e);
		
		if(this.employees.removeIf(existingEmpl -> existingEmpl.getId()==pe.get().getId()))
			// 1 row affected.
			return transformToDomain(pe.get());
		else 
			// 0 rows affected.
			return Optional.empty();
	}

	@Override
	public Optional<Employee> get(Employee e) {
		
	   Optional<PersistedEmployee> pe = transformToPersist(e);
       Optional<PersistedEmployee> persisted = employees.stream().filter(a -> a.getId()==(pe.get().getId())).findFirst();
        
       return transformToDomain(persisted.get());  
	}
	
	@Override
	public Optional<Employee> findById(Long id) {
		 
		Optional<PersistedEmployee> pe = employees.stream().filter(a -> (a.getId()==id)).findFirst();
	        
		if (pe.isPresent())
	            return transformToDomain(pe.get());
	        else
	            return Optional.empty(); 
	}

	@Override
	public List<Employee> getAll() {
		
		List<Employee> employeeList = new LinkedList<Employee>();
		this.employees.forEach(pe -> employeeList.add(transformToDomain(pe).get()));
		return employeeList;
		
	}
	
	@Override
	public long count() {
		return this.employees.size();
	}
	
	
	//
	// ---------------- HELP! METHODS
	//
	
	private static Optional<Employee> transformToDomain(PersistedEmployee persisted) {
		
		Employee employee = null;
		if(persisted!=null){
			employee = new Employee();
			employee.setId(persisted.getId());
			employee.setName(persisted.getName());
			employee.setAge(persisted.getAge());
			employee.setPosition(persisted.getPosition());
			employee.setOrganizationId(persisted.getOrganizationId());
			employee.setDepartmentId(persisted.getDepartmentId());
			
		}
		
		return Optional.ofNullable(employee);
		
	}
	
	private static Optional<PersistedEmployee> transformToPersist(Employee employee) {
		
		PersistedEmployee persisted = null;
		if(employee!=null){
			persisted = new PersistedEmployee();
			persisted.setName(employee.getName());
			persisted.setAge(employee.getAge());
			persisted.setPosition(employee.getPosition());
			persisted.setOrganizationId(employee.getOrganizationId());
			persisted.setDepartmentId(employee.getDepartmentId());
			
		}
		
		return Optional.ofNullable(persisted);
		
	}

}