package com.holidaysystem.resource;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.holidaysystem.entity.EmployeeEntity;
import com.holidaysystem.model.EmployeeRequest;
import com.holidaysystem.model.EmployeeResponse;
import com.holidaysystem.repository.EmployeeRepository;

@Path("/employee")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
//@Stateless
public class EmployeeResource {

    @Inject
    EmployeeRepository employeeRepository;

    @GET
    @Path("/all")
    public Response getUsers() {
    	List<EmployeeEntity> entities = employeeRepository.getUsers();
    	List<EmployeeResponse> employees = new ArrayList<>();
    	for(EmployeeEntity entity: entities) {
    		EmployeeResponse employee = new EmployeeResponse();
    		employee.setId(entity.getId());
    		employee.setFirstName(entity.getFirstname());
    		employee.setLastName(entity.getLastname());
    		employee.setCreated(entity.getCreated().toString());
    		employee.setModified(entity.getModified().toString());
    		
    		employees.add(employee);
    	}
    	
        return Response.ok(employees).build();
    }
    
    
    @GET()
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@PathParam("id") UUID id) {
    	System.out.println("userId = " + id);
    	EmployeeEntity user = employeeRepository.findById(id);
        return Response.ok(user).build();
    }
    
    @POST()
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createUser(EmployeeRequest userRequest) {
    	
    	EmployeeEntity user = new EmployeeEntity();
    	user.setId(UUID.randomUUID());
    	user.setEmail(userRequest.getEmail());
    	user.setFirstname(userRequest.getFirstName());
    	user.setLastname(userRequest.getLastName());
    	
    	employeeRepository.save(user);
    	
        return Response.ok(user).build();
    }
}
