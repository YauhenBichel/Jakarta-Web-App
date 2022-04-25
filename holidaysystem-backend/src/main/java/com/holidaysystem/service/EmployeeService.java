/*
 *     Copyright 2022-2022 Yauhen Bichel yb3129h@gre.ac.uk OR bichel.eugen@gmail.com 
 *     Student Id 001185491
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.holidaysystem.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.transaction.Transactional;

import com.holidaysystem.common.exception.RecordNotFoundException;
import com.holidaysystem.entity.AccountEntity;
import com.holidaysystem.entity.EmployeeEntity;
import com.holidaysystem.entity.HolidayDetailsEntity;
import com.holidaysystem.enumeration.HolidayStatusEnum;
import com.holidaysystem.mapper.EmployeeMapper;
import com.holidaysystem.model.EmployeeModel;
import com.holidaysystem.repository.IAccountRepository;
import com.holidaysystem.repository.IEmployeeRepository;
import com.holidaysystem.repository.IHolidayDetailsRepository;
import com.holidaysystem.vo.NewEmployeeRequest;

/**
 * Employee service provides employee details
 * @author yauhen bichel yb3129h@gre.ac.uk Student Id 001185491
 *
 */
@ApplicationScoped
@Default
public class EmployeeService implements IEmployeeService {
	@Inject
	IEmployeeRepository employeeRepository;
	@Inject
	IAccountRepository accountRepository;
	@Inject
	IHolidayDetailsRepository holidayDetailsRepository;
	@Inject
	EmployeeMapper employeeMapper;
    
    @Transactional
    public List<EmployeeModel> getEmployees() {
    	
    	List<EmployeeModel> employeeModels = employeeRepository.getEmployeeModels();
    	
    	return employeeModels;
    }
    
    @Transactional
    public List<EmployeeModel> getEmployeesByDate(LocalDateTime date) {
    	
    	List<EmployeeModel> employeeModels = employeeRepository.getEmployeeModelsOnHolidaysByDate(date);
    	
    	return employeeModels;
    }
    
    @Transactional
    public List<EmployeeModel> getEmployeesByDateAndHolidayStatus(LocalDateTime date, HolidayStatusEnum holidayStatus) {
    	
    	List<EmployeeModel> employeeModels = employeeRepository.getEmployeeModelsByDateAndHolidayStatus(date, holidayStatus);
    	
    	return employeeModels;
    }
    
    @Transactional
    public EmployeeModel findById(UUID employeeId) {
    	
    	EmployeeEntity employeeEntity = employeeRepository.findById(employeeId);
    	
    	if(employeeEntity == null) 
    		throw new RecordNotFoundException("Employee not found");
    	
    	AccountEntity accountEntity = accountRepository.findById(employeeEntity.getAccountId());
    	HolidayDetailsEntity holidayDetailsEntity = holidayDetailsRepository.findByEmployeeId(employeeId);
    	
    	EmployeeModel empployeeModel = employeeMapper.toEmployeeModel(employeeEntity, accountEntity, holidayDetailsEntity);
    	
    	return empployeeModel;
    }
    
    @Transactional
    public EmployeeModel create(NewEmployeeRequest employeeRequest) {
    	
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
