package com.holidaysystem.mapper;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;

import com.holidaysystem.entity.AccountEntity;
import com.holidaysystem.entity.EmployeeEntity;
import com.holidaysystem.entity.HolidayDetailsEntity;
import com.holidaysystem.enumeration.DepartmentEnum;
import com.holidaysystem.model.EmployeeModel;
import com.holidaysystem.enumeration.EmployeeRoleEnum;
import com.holidaysystem.vo.NewEmployeeRequest;
import com.holidaysystem.vo.EmployeeResponse;

/**
 * Employee mapper
 * @author yauhen bichel yb3129h@gre.ac.uk Student Id 001185491
 *
 */
@ApplicationScoped
public class EmployeeMapper {
	public EmployeeEntity toEntity(NewEmployeeRequest request) {
		EmployeeEntity entity = new EmployeeEntity();
    	entity.setId(UUID.randomUUID());
    	entity.setAccountId(request.getAccountId());
    	entity.setRole(request.getRole());
    	entity.setDepartment(request.getDepartment());
    	entity.setFirstName(request.getFirstName());
    	entity.setLastName(request.getLastName());
    	entity.setYears(request.getYears());
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
		employee.setYears(entity.getYears());
		
		return employee;
	}
	
	public EmployeeModel toEmployeeModel(EmployeeEntity entity) {
		EmployeeModel employee = new EmployeeModel();
		employee.setId(entity.getId());
		employee.setAccountId(entity.getAccountId());
		employee.setFirstName(entity.getFirstName());
		employee.setLastName(entity.getLastName());
		employee.setRole(EmployeeRoleEnum.valueOf(entity.getRole()));
		employee.setDepartment(DepartmentEnum.valueOf(entity.getDepartment()));
		employee.setYears(entity.getYears());
		
		return employee;
	}
	
	public EmployeeModel toEmployeeModel(EmployeeEntity employeeEntity, AccountEntity accountEntity, HolidayDetailsEntity holidayDetailsEntity) {
		EmployeeModel employee = new EmployeeModel();
		employee.setId(employeeEntity.getId());
		employee.setAccountId(employeeEntity.getAccountId());
		employee.setEmail(accountEntity.getEmail());
		employee.setFirstName(employeeEntity.getFirstName());
		employee.setLastName(employeeEntity.getLastName());
		employee.setRole(EmployeeRoleEnum.valueOf(employeeEntity.getRole()));
		employee.setDepartment(DepartmentEnum.valueOf(employeeEntity.getDepartment()));
		employee.setYears(employeeEntity.getYears());
		employee.setTotalDays(holidayDetailsEntity.getTotalDays());
		employee.setTakenDays(holidayDetailsEntity.getTakenDays());
		
		return employee;
	}
	
	public EmployeeResponse toResponse(EmployeeModel model) {
		EmployeeResponse employee = new EmployeeResponse();
		employee.setId(model.getId());
		employee.setAccountId(model.getAccountId());
		employee.setEmail(model.getEmail());
		employee.setFirstName(model.getFirstName());
		employee.setLastName(model.getLastName());
		employee.setRole(model.getRole().name());
		employee.setDepartment(model.getDepartment().name());
		employee.setYears(model.getYears());
		employee.setTotalDays(model.getTotalDays());
		employee.setTakenDays(model.getTakenDays());
		employee.setHolidayStatus(model.getHolidayStatus().name());
		
		return employee;
	}
}
