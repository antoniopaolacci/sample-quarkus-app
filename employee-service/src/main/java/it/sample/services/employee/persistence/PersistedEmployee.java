package it.sample.services.employee.persistence;

import it.sample.services.employee.domain.Employee;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "employee")
@NamedQuery(name = "PersistedEmployee.findAll", query = "SELECT pe FROM PersistedEmployee pe")
@NamedQuery(name = "PersistedEmployee.count",   query = "SELECT count(pe) FROM PersistedEmployee pe")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PersistedEmployee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

	@Column(name = "name", length = 255, nullable = false)
	private String name;
	
	@Column(name = "age")
	private int age;

	@Column(name = "position", length = 255, nullable = false)
	private String position;
	
	@Column(name = "organizationId")
	private long organizationId;
	
	@Column(name = "departmentId")
	private long departmentId;
	
	//Other constructors:
	public PersistedEmployee(Employee e){
		this.id = e.getId();
		this.name = e.getName();
		this.age = e.getAge();
		this.position = e.getPosition();
		this.organizationId = e.getOrganizationId();
		this.departmentId = e.getDepartmentId();
	}
	
}