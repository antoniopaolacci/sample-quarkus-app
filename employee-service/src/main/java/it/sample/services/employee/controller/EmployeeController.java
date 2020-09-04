package it.sample.services.employee.controller;

import it.sample.services.employee.domain.Employee;
import it.sample.services.employee.repository.EmployeeRepository;
import it.sample.services.employee.rest.client.DepartmentRestClient;

import java.util.Set;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Path("/employees")
@Produces(MediaType.APPLICATION_JSON)
public class EmployeeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);

    @Inject
    EmployeeRepository repository;
    
    @Inject
    @RestClient
    DepartmentRestClient departmentRestClient;
    
    @GET
    public Set<Employee> findAll() {
    	
    	LOGGER.info("Employee find all.");
        
    	Set<Employee> employees = repository.findAll();  
        employees.forEach(e -> departmentRestClient.getDepartmentById(e.getDepartmentId())); 
        
        return employees;
    }

    @POST
    public Employee add(@Valid Employee employee) {
        LOGGER.info("Employee add: {}", employee);
        return repository.add(employee);
    }

    @Path("/{id}")
    @GET
    public Employee findById(@PathParam("id") Long id) {
        LOGGER.info("Employee find: id={}", id);
        return repository.findById(id);
    }

    @Path("/department/{departmentId}")
    @GET
    public Set<Employee> findByDepartment(@PathParam("departmentId") Long departmentId) {
        LOGGER.info("Employee find: departmentId={}", departmentId);
        return repository.findByDepartment(departmentId);
    }

    @Path("/organization/{organizationId}")
    @GET
    public Set<Employee> findByOrganization(@PathParam("organizationId") Long organizationId) {
        LOGGER.info("Employee find: organizationId={}", organizationId);
        return repository.findByOrganization(organizationId);
    }
    
    @Path("/version")
    @GET
    public String getVersion() {
    	String version = "Microservice version=1.1.2";
        LOGGER.info("Microservice version={}", version);
        return version;
    }

}//end class