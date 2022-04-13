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

import com.holidaysystem.enumeration.EmployeeRoleEnum;

/**
 * REST API for roles resource
 * @author yauhen bichel yb3129h@gre.ac.uk Student Id 001185491
 *
 */
@Path("/role")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
//@ServletSecurity(value = @HttpConstraint(rolesAllowed = {"admin_role"}))
public class RolesResource {
    
	private static final Logger logger = Logger.getLogger(RolesResource.class);
	
    @GET
    @Path("/all")
    public Response getRoles() {
    	
    	logger.debug("getRoles()");
    	
    	List<String> roles = new ArrayList<>();
    	
    	for(EmployeeRoleEnum value: EmployeeRoleEnum.values()) {
    		roles.add(value.name());
    	}
    	
        return Response.ok(roles)
        		.header("Access-Control-Allow-Origin", "*")
        		.build();
    }
   
}
