package it.sample.services.department.controller;

import it.sample.services.department.domain.Department;
import it.sample.services.department.repository.DepartmentRepository;

import java.util.Set;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/departments")
@Produces(MediaType.APPLICATION_JSON)
public class DepartmentController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentController.class);

    @Inject
    DepartmentRepository repository;
    
    @Path("/hello")
    @GET
    public String hello() {
        LOGGER.info("Hello!");
       	return "hello";
   }
    
    @Path("/")
    @POST
    public Department add(@Valid Department department) {
        LOGGER.info("Department add: {}", department);
        return repository.add(department);
    }

    @Path("/{id}")
    @GET
    public Department findById(@PathParam("id") Long id) {
        LOGGER.info("Department find: id={}", id);
        return repository.findById(id);
    }

    @GET
    public Set<Department> findAll() {
        LOGGER.info("Department find all.");
        return repository.findAll();
    }

    @Path("/organization/{organizationId}")
    @GET
    public Set<Department> findByOrganization(@PathParam("organizationId") Long organizationId) {
        LOGGER.info("Department find: organizationId={}", organizationId);
        return repository.findByOrganization(organizationId);
    }
	
}