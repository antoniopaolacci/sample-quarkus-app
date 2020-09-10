package it.sample.services.employee.external.message;

import it.sample.services.employee.domain.Department;
import it.sample.services.employee.domain.Employee;
import it.sample.services.employee.domain.Organization;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.fasterxml.jackson.annotation.JsonRootName;


@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonRootName(value="EmployeeResponse")
public class EmployeeResponse {

	@NotNull
	private Employee employee;
	
	@NotNull
	private Department department;
	
	@NotNull
	private Organization organization;
	
}