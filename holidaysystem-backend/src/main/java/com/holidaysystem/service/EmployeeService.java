package com.holidaysystem.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import com.holidaysystem.entity.AccountEntity;
import com.holidaysystem.entity.EmployeeEntity;
import com.holidaysystem.entity.HolidayDetailsEntity;
import com.holidaysystem.mapper.EmployeeMapper;
import com.holidaysystem.model.EmployeeModel;
import com.holidaysystem.repository.AccountRepository;
import com.holidaysystem.repository.EmployeeRepository;
import com.holidaysystem.repository.HolidayDetailsRepository;
import com.holidaysystem.vo.EmployeeRequest;

/**
 * Employee service provides employee details
 * @author yauhen bichel yb3129h@gre.ac.uk Student Id 001185491
 *
 */
@ApplicationScoped
public class EmployeeService {
	@Inject
    EmployeeRepository employeeRepository;
	@Inject
    AccountRepository accountRepository;
	@Inject
    HolidayDetailsRepository holidayDetailsRepository;
    @Inject
    EmployeeMapper employeeMapper;
    
    @Transactional
    public List<EmployeeModel> getEmployees() {
    	
    	List<EmployeeEntity> entities = employeeRepository.getEmployees();
    	
    	List<EmployeeModel> employeeModels = new ArrayList<>();
    	
    	for(EmployeeEntity entity: entities) {
    		EmployeeModel empployeeModel = employeeMapper.toEmployeeModel(entity);
    		employeeModels.add(empployeeModel);
    	}
    	
    	return employeeModels;
    }
    
    @Transactional
    public EmployeeModel findById(UUID employeeId) {
    	
    	EmployeeEntity employeeEntity = employeeRepository.findById(employeeId);
    	
    	if(employeeEntity == null) 
    		throw new RuntimeException("Employee not found");
    	
    	AccountEntity accountEntity = accountRepository.findById(employeeEntity.getAccountId());
    	HolidayDetailsEntity holidayDetailsEntity = holidayDetailsRepository.findByEmployeeId(employeeId);
    	
    	EmployeeModel empployeeModel = employeeMapper.toEmployeeModel(employeeEntity, accountEntity, holidayDetailsEntity);
    	
    	return empployeeModel;
    }
    
    @Transactional
    public EmployeeModel create(EmployeeRequest employeeRequest) {
    	
    	EmployeeEntity employeeEntity = employeeMapper.toEntity(employeeRequest);
    	boolean saved = employeeRepository.save(employeeEntity);
    	
    	if(!saved) 
    		throw new RuntimeException("Failed to add an employee");
    	
    	HolidayDetailsEntity holidayDetails = new HolidayDetailsEntity();
    	holidayDetails.setId(UUID.randomUUID());
    	holidayDetails.setEmployeeId(employeeEntity.getId());
    	holidayDetails.setTotalDays(30);
    	holidayDetails.setTakenDays(0);
    	holidayDetails.setCreated(LocalDateTime.now());
    	holidayDetails.setModified(LocalDateTime.now());
    	
    	holidayDetailsRepository.save(holidayDetails);
    	
    	AccountEntity accountEntity = accountRepository.findById(employeeEntity.getAccountId());
    	
    	EmployeeModel empployeeModel = employeeMapper.toEmployeeModel(employeeEntity, accountEntity, holidayDetails);
    	
    	return empployeeModel;
    }
}
