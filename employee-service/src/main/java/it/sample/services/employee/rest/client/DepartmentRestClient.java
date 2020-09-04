package it.sample.services.employee.rest.client;

import it.sample.services.employee.domain.Department;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Singleton
@Path("/departments")
@RegisterRestClient
public interface DepartmentRestClient {

    @GET
    @Path("/{departmentId}")
    @Produces(MediaType.APPLICATION_JSON)
    Department getDepartmentById(@PathParam("departmentId") Long departmentId);

}