package com.holidaysystem.mapper;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;

import com.holidaysystem.entity.EmployeeEntity;
import com.holidaysystem.model.EmployeeRequest;
import com.holidaysystem.model.EmployeeResponse;

@ApplicationScoped
public class EmployeeMapper {
	public EmployeeEntity toEntity(EmployeeRequest request) {
		EmployeeEntity entity = new EmployeeEntity();
    	entity.setId(UUID.randomUUID());
    	entity.setAccountId(request.getAccountId());
    	entity.setRole(request.getRole());
    	entity.setDepartment(request.getDepartment());
    	entity.setFirstName(request.getFirstName());
    	entity.setLastName(request.getLastName());
    	entity.setCreated(LocalDateTime.now());
    	entity.setModified(LocalDateTime.now());
    	
    	return entity;
	}
	
	public EmployeeResponse toResponse(EmployeeEntity entity) {
		EmployeeResponse employee = new EmployeeResponse();
		employee.setId(entity.getId());
		employee.setAccountId(entity.getAccountId());
		employee.setFirstName(entity.getFirstName());
		employee.setLastName(entity.getLastName());
		employee.setRole(entity.getRole());
		employee.setDepartment(entity.getDepartment());
		employee.setCreated(entity.getCreated().toString());
		employee.setModified(entity.getModified().toString());
		
		return employee;
	}
}