package it.sample.services.employee.storage;

import io.quarkus.runtime.annotations.RegisterForReflection;
import it.sample.services.employee.domain.Employee;
import it.sample.services.employee.persistence.PersistedEmployee;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

@RegisterForReflection(fields = false, methods = true)
@ApplicationScoped
public class PersistentStorageService implements StorageService {

	private final EntityManager db;
	private long tableSize = 0;

	@Inject
	public PersistentStorageService(EntityManager db) {
		this.db = db;
	}
	
	@Override
	@Transactional
	public Optional<Employee> add(Employee e) {
		
		Optional<PersistedEmployee> pe = transformToPersist(e);
		db.persist(pe.get());
		
		return transformToDomain(pe.get());
	}

	@Override
	@Transactional
	public Optional<Employee> update(Employee e) {
		
		Optional<PersistedEmployee> pe = transformToPersist(e);
		PersistedEmployee result_pe = db.find(PersistedEmployee.class,pe.get().getId());
		
		if (result_pe != null) {
			result_pe.setName(pe.get().getName());
			result_pe.setAge(pe.get().getAge());
			result_pe.setPosition(pe.get().getPosition());
			result_pe.setOrganizationId(pe.get().getOrganizationId());
			result_pe.setDepartmentId(pe.get().getDepartmentId());
			db.persist(result_pe);
		}
		
		return transformToDomain(result_pe);
	}

	@Override
	@Transactional
	public Optional<Employee> delete(Employee e) {
		
		Optional<PersistedEmployee> pe = transformToPersist(e);
		PersistedEmployee result_pe = db.find(PersistedEmployee.class,pe.get().getId());
		
		if (result_pe != null) {
			// 1 row affected.
			db.remove(result_pe);
		}
			// else 0 row affected, means returns an Optional empty.
		
		return transformToDomain(result_pe);
	}
	
	@Override
	public Optional<Employee> get(Employee e) {
		
		Optional<PersistedEmployee> pe = transformToPersist(e);
		PersistedEmployee result_pe = db.find(PersistedEmployee.class,pe.get().getId());	
		return transformToDomain(result_pe);
	}
	
	@Override
	public Optional<Employee> findById(Long id) {
		PersistedEmployee result_pe = db.find(PersistedEmployee.class, id);
		return transformToDomain(result_pe);
	}

	@Override
	public List<Employee> getAll() {
		
		final TypedQuery<PersistedEmployee> query = (TypedQuery) db.createNamedQuery("PersistedEmployee.findAll", PersistedEmployee.class);
		final List<PersistedEmployee> persistedList = query.getResultList();
		
		List<Employee> employeeList = new LinkedList<Employee>();
		persistedList.forEach(pe -> employeeList.add(transformToDomain(pe).get()));
		
		return employeeList;
	}
	
	@Override
	public long count() {
		
		try {
			final Query query = db.createNamedQuery("PersistedEmployee.count");
			tableSize = (Long) query.getSingleResult();
			
			return this.tableSize;
			
		} catch (final Exception e) {
			
			return this.tableSize;
		}
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