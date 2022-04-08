package com.holidaysystem.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;

import com.holidaysystem.model.DepartmentEnum;
import com.holidaysystem.model.EmployeeRoleEnum;

/**
 * 
 * @author yauhen bichel
 *
 */
@Path("/role")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RolesResource {
    
    @GET
    @Path("/all")
    public Response getRoles() {
    	List<String> roles = new ArrayList<>();
    	
    	for(EmployeeRoleEnum value: EmployeeRoleEnum.values()) {
    		roles.add(value.name());
    	}
    	
        return Response.ok(roles)
        		.header("Access-Control-Allow-Origin", "*")
        		.build();
    }
   
}
