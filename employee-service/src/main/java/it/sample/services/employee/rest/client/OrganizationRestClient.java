package it.sample.services.employee.rest.client;

import it.sample.services.employee.domain.Organization;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Singleton
@Path("/organizations")
@RegisterRestClient
public interface OrganizationRestClient {

    @GET
    @Path("/{organizationId}")
    @Produces(MediaType.APPLICATION_JSON)
    Organization getOrganizationById(@PathParam("organizationId") Long organizationId);

}