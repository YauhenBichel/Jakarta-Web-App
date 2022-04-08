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

/**
 * 
 * @author yauhen bichel
 *
 */
@Path("/department")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DepartmentsResource {
    
    @GET
    @Path("/all")
    public Response getDepartments() {
    	List<String> roles = new ArrayList<>();
    	
    	for(DepartmentEnum value: DepartmentEnum.values()) {
    		roles.add(value.name());
    	}
    	
        return Response.ok(roles)
        		.header("Access-Control-Allow-Origin", "*")
        		.build();
    }
   
}
