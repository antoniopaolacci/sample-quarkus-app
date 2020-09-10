package it.sample.services.employee.controller;

import it.sample.services.employee.domain.Employee;
import it.sample.services.employee.external.message.EmployeeResponse;
import it.sample.services.employee.repository.EmployeeRepository;
import it.sample.services.employee.rest.client.DepartmentRestClient;
import it.sample.services.employee.rest.client.OrganizationRestClient;

import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Path("/employees")
@Produces(MediaType.APPLICATION_JSON)
public class EmployeeController {

    private static final Logger log = LoggerFactory.getLogger(EmployeeController.class);

    @ConfigProperty(name = "config.version")
    String version;
    
    @Inject
    EmployeeRepository repository;
    
    @Inject
    @RestClient
    DepartmentRestClient departmentRestClient;
    
    @Inject
    @RestClient
    OrganizationRestClient organizationRestClient;
    
    @GET
    public Set<Employee> findAll() {
    	log.info("Employee find all.");
    	Set<Employee> employees = repository.findAll();  
        return employees;
    }

    @POST
    public Employee add(@Valid Employee employee) {
        log.info("Employee add: {}", employee);
        return repository.add(employee);
    }

    @Path("/{id}")
    @GET
    public Employee findById(@PathParam("id") Long id) {
        log.info("Employee find: id={}", id);
        return repository.findById(id);
    }

    @Path("/department/{departmentId}")
    @GET
    public Set<Employee> findByDepartment(@PathParam("departmentId") Long departmentId) {
        log.info("Employee find: departmentId={}", departmentId);
        return repository.findByDepartment(departmentId);
    }

    @Path("/organization/{organizationId}")
    @GET
    public Set<Employee> findByOrganization(@PathParam("organizationId") Long organizationId) {
        log.info("Employee find: organizationId={}", organizationId);
        return repository.findByOrganization(organizationId);
    }
    
    @Path("/version")
    @GET
    public String getVersion() {
    	log.info("Project version={}", this.version);
    	return this.version;
    }
    
    
    /*
     *  {
     *		"employee": {
     *			"id": 1,
     *			"organizationId": 1,
     *			"departmentId": 1,
     *			"name": "John Smith",
     *			"age": 30,
     *			"position": "Developer"
     *		},
     *		"department": {
     *			"id": 1,
     *			"organizationId": 1,
     *			"name": "Test1"
     *		}
     *	},
     *	{
     *		"employee": {
     *			"id": 2,
     *			"organizationId": 1,
     *			"departmentId": 1,
     *			"name": "Paul Walker",
     *			"age": 40,
     *			"position": "Architect"
     *		},
     *		"department": {
     *			"id": 1,
     *			"organizationId": 1,
     *			"name": "Test1"
     *		}
     *	}    
     */


    @Path("/details")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response list() {
    	
    	try { 
    	
	    	Set<Employee> employees = repository.findAll(); 	    	
	    	Set<EmployeeResponse> employeeResponses = new HashSet<EmployeeResponse>();	    	
	        employees.forEach(e -> employeeResponses.add(new EmployeeResponse(e, 
	        																  departmentRestClient.getDepartmentById(e.getDepartmentId()), 
	        																  organizationRestClient.getOrganizationById(e.getOrganizationId()))));
	    	
	        return Response.ok(employeeResponses).build();
    	
    	} catch (Exception e) {
    		
    		log.error("Error: ", e);
    		
    		return Response.status(Response.Status.NOT_FOUND).build();
    	
    	}
        
    }

}//end class