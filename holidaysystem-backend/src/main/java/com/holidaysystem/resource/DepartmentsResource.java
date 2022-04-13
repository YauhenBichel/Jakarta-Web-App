package com.holidaysystem.resource;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.logging.Logger;

import java.util.ArrayList;
import java.util.List;

import com.holidaysystem.enumeration.DepartmentEnum;

/**
 * REST API for departments resource
 * @author yauhen bichel yb3129h@gre.ac.uk Student Id 001185491
 *
 */
@Path("/department")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
//@ServletSecurity(value = @HttpConstraint(rolesAllowed = {"USER", "ADMIN"}))
public class DepartmentsResource {
    
	private static final Logger logger = Logger.getLogger(DepartmentsResource.class);
	
    @GET
    @Path("/all")
    public Response getDepartments() {
    	logger.debug("getDepartments()");
    	
    	List<String> departments = new ArrayList<>();
    	
    	for(DepartmentEnum value: DepartmentEnum.values()) {
    		departments.add(value.name());
    	}
    	
        return Response.ok(departments)
        		.build();
    }
   
}
