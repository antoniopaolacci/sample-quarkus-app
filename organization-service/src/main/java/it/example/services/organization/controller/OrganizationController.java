package it.example.services.organization.controller;

import it.example.services.organization.domain.Organization;
import it.example.services.organization.repository.OrganizationPersistenceRepository;
import it.example.services.organization.repository.OrganizationRepository;

import java.util.List;
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

@Path("/organizations")
@Produces(MediaType.APPLICATION_JSON)
public class OrganizationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrganizationController.class);

//  @Inject
//  OrganizationRepository repository;
    
    @Inject
    OrganizationPersistenceRepository repository;

    @Path("/hello")
    @GET
    public String hello() {
        LOGGER.info("Hello!");
       	return "hello";
    }
    
    @POST
    public Organization add(@Valid Organization organization) {
        LOGGER.info("Organization add: {}", organization);
        return repository.add(organization);
    }

    @GET
    public List<Organization> findAll() {
        LOGGER.info("Organization find all.");
        return repository.findAll();
    }

    @Path("/{id}")
    @GET
    public Organization findById(@PathParam("id") Long id) {
        LOGGER.info("Organization find: id={}", id);
        return repository.findById(id);
    }

}