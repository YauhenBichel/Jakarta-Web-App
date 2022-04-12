package com.holidaysystem.resource;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.logging.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.holidaysystem.mapper.EmployeeMapper;
import com.holidaysystem.model.EmployeeModel;
import com.holidaysystem.vo.EmployeeRequest;
import com.holidaysystem.vo.EmployeeResponse;
import com.holidaysystem.repository.EmployeeRepository;
import com.holidaysystem.service.EmployeeService;

/**
 * REST API for employee resource
 * @author yauhen bichel yb3129h@gre.ac.uk Student Id 001185491
 *
 */
@Path("/employee")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class EmployeeResource {

	private static final Logger logger = Logger.getLogger(EmployeeResource.class);
	
	@Inject
    EmployeeMapper employeeMapper;
	@Inject
    EmployeeRepository employeeRepository;
	@Inject
    EmployeeService employeeService;

    @GET
    @Path("/all")
    public Response getEmployees() {
    	
    	logger.debug("getEmployees()");
    	
    	List<EmployeeModel> empployeeModels = employeeService.getEmployees();
    	List<EmployeeResponse> employees = new ArrayList<>();
    	for(EmployeeModel empployeeModel: empployeeModels) {
    		EmployeeResponse employee = employeeMapper.toResponse(empployeeModel);
    		employees.add(employee);
    	}
    	
        return Response.ok(employees)
        		.header("Access-Control-Allow-Origin", "*")
        		.build();
    }
    
    @GET()
    @Path("/{id}")
    public Response getEmployee(@PathParam("id") UUID id) {
    	EmployeeModel empployeeModel = employeeService.findById(id);
    	EmployeeResponse employee = employeeMapper.toResponse(empployeeModel);
        return Response.ok(employee)
        		.header("Access-Control-Allow-Origin", "*")
        		.build();
    }
    
    @POST()
    public Response createEmployee(EmployeeRequest employeeRequest) {    	
    	EmployeeModel employeeModel = employeeService.create(employeeRequest);
    	EmployeeResponse employee = employeeMapper.toResponse(employeeModel);
    	
        return Response.ok(employee)
        		.header("Access-Control-Allow-Origin", "*")
        		.build();
    }
}
