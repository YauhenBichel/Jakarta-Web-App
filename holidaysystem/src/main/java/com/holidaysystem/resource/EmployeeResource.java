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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.holidaysystem.entity.EmployeeEntity;
import com.holidaysystem.mapper.EmployeeMapper;
import com.holidaysystem.model.EmployeeRequest;
import com.holidaysystem.model.EmployeeResponse;
import com.holidaysystem.repository.EmployeeRepository;

@Path("/employee")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EmployeeResource {

    @Inject
    EmployeeRepository employeeRepository;
    @Inject
    EmployeeMapper employeeMapper;

    @GET
    @Path("/all")
    public Response getEmployees() {
    	List<EmployeeEntity> entities = employeeRepository.getEmployees();
    	List<EmployeeResponse> employees = new ArrayList<>();
    	for(EmployeeEntity entity: entities) {
    		EmployeeResponse employee = employeeMapper.toResponse(entity);
    		employees.add(employee);
    	}
    	
        return Response.ok(employees).build();
    }
    
    @GET()
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEmployee(@PathParam("id") UUID id) {
    	EmployeeEntity entity = employeeRepository.findById(id);
    	EmployeeResponse employee = employeeMapper.toResponse(entity);
        return Response.ok(employee).build();
    }
    
    @POST()
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createEmployee(EmployeeRequest employeeRequest) {    	
    	EmployeeEntity entity = employeeMapper.toEntity(employeeRequest);
    	employeeRepository.save(entity);
    	EmployeeResponse employee = employeeMapper.toResponse(entity);
    	
        return Response.ok(employee).build();
    }
}