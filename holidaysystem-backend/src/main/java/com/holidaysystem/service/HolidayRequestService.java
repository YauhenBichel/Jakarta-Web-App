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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.transaction.Transactional;

import org.jboss.logging.Logger;

import com.holidaysystem.Constants;
import com.holidaysystem.common.exception.RecordNotFoundException;
import com.holidaysystem.comparator.HolidayRequestModelComparator;
import com.holidaysystem.constraints.IConstraintsValidator;
import com.holidaysystem.entity.EmployeeEntity;
import com.holidaysystem.entity.HolidayRequestEntity;
import com.holidaysystem.model.AlternativeDatesModel;
import com.holidaysystem.model.EmployeeModel;
import com.holidaysystem.model.HolidayRequestModel;
import com.holidaysystem.model.PrioritizedRequestModel;
import com.holidaysystem.enumeration.HolidayRequestStatusEnum;
import com.holidaysystem.mapper.HolidayRequestMapper;
import com.holidaysystem.message.HolidayRequestMQProducer;
import com.holidaysystem.repository.IEmployeeRepository;
import com.holidaysystem.repository.IHolidayDetailsRepository;
import com.holidaysystem.repository.IHolidayRequestRepository;
import com.holidaysystem.vo.HolidayRequest;

import javax.inject.Inject;

/**
 * HolidayRequest service provides user register and login
 * @author yauhen bichel yb3129h@gre.ac.uk Student Id 001185491
 *
 */
@ApplicationScoped
@Default
public class HolidayRequestService implements IHolidayRequestService {
	
	private static final Logger logger = Logger.getLogger(HolidayRequestService.class);
	
	@Inject
    private IHolidayRequestRepository holidayRequestRepository;
	@Inject
    private IHolidayDetailsRepository holidayDetailsRepository;
	@Inject
    private IEmployeeRepository employeeRepository;
    @Inject
    private HolidayRequestMapper holidayRequestMapper;
    @Inject
    private HolidayRequestMQProducer holidayRequestMQProducer;
    @Inject
    private IConstraintsValidator constraintValidator;
    @Inject
    private HolidayRequestModelComparator comparator;
	
    @Transactional
	public List<HolidayRequestModel> getHolidayRequests() {
		List<HolidayRequestModel> models = holidayRequestRepository.getHolidayRequestModels();
    	return models;
	}
    
	@Transactional
	public List<HolidayRequestModel> getHolidayRequests(int offset, int limit) {
		List<HolidayRequestModel> models = holidayRequestRepository.getHolidayRequestModels(offset, limit);
    	return models;
	}
	
	@Transactional
	public List<HolidayRequestModel> getHolidayRequestsByStatus(HolidayRequestStatusEnum status) {
		List<HolidayRequestModel> models = holidayRequestRepository.getHolidayRequestModelsByStatus(status);
		return models;
	}
	
	@Transactional
	public HolidayRequestEntity findEntityById(UUID requestId) {
		HolidayRequestEntity entity = holidayRequestRepository.findById(requestId);
    	return entity;
	}
	
	@Transactional
	public HolidayRequestModel fetchModelById(UUID requestId) {
		HolidayRequestEntity entity = holidayRequestRepository.findById(requestId);
		HolidayRequestModel model = holidayRequestMapper.toModel(entity);
    	return model;
	}
	
	@Transactional
	public void addHolidayRequest(UUID requestId, HolidayRequest holidayRequest) {
		HolidayRequestEntity entity = holidayRequestMapper.toEntity(requestId, holidayRequest);
    	holidayRequestRepository.save(entity);
    	holidayRequestMQProducer.publish(requestId, holidayRequest);
	}

	@Transactional
	public HolidayRequestEntity update(UUID requestId, HolidayRequest holidayRequest) {
		HolidayRequestEntity dbEntity = this.findEntityById(requestId);
    	
    	if(dbEntity != null) {
    		HolidayRequestEntity entity = holidayRequestMapper.toEntity(holidayRequest, dbEntity);
        	holidayRequestRepository.update(requestId, entity);

        	return entity;
    	}
    	
    	logger.info("request with id: " + requestId + " not found");
    	throw new RecordNotFoundException("Holiday request is not found");
	}
	
	@Transactional
	public boolean validate(UUID requestId) {
		
		HolidayRequestEntity dbHolidayRequestEntity = this.findEntityById(requestId);
		if(dbHolidayRequestEntity == null) {
			logger.error("holiday request not found");
			throw new RecordNotFoundException("holiday request not found");
		}
		
		EmployeeEntity dbEmployeeEntity = employeeRepository.findById(dbHolidayRequestEntity.getEmployeeId());
		if(dbEmployeeEntity == null) {
			logger.error("employee not found");
			throw new RecordNotFoundException("employee not found");
		}
		
		List<EmployeeModel> employeeModels = employeeRepository.getEmployeeModelsByDepartmentId(dbEmployeeEntity.getDepartment());
		LocalDateTime now = LocalDateTime.now();
		int year = LocalDate.now().getYear();
		LocalDate dec22 = LocalDate.of(year, 12, 22);
		LocalDate jan3 = LocalDate.of(year + 1, 1, 3);
		
		if(now.isAfter(LocalDateTime.of(dec22, LocalTime.of(0, 0))) && 
				now.isBefore(LocalDateTime.of(jan3, LocalTime.of(0, 0)))) {
		
			final int percentOnDuty = LocalDate.now().getMonthValue() == 8 ? 
					Constants.PERCENT_MEMBERS_AUGUST_ON_DUTY:
				Constants.PERCENT_MEMBERS_ON_DUTY;
			
		return constraintValidator.hasHeadOrDeputyHead(employeeModels) &&
				constraintValidator.hasManagerAndSeniorMemeber(employeeModels) &&
				constraintValidator.hasSpecificPercentMemebersOnDuty(employeeModels, percentOnDuty);
		}
		
		return true;
	}

	@Override
	public List<PrioritizedRequestModel> getPrioritizedHolidayRequests(LocalDateTime date, HolidayRequestStatusEnum requestStatus) {
		List<PrioritizedRequestModel> prioritizedRequests = new LinkedList<>();
		
		List<HolidayRequestModel> holidayRequests = holidayDetailsRepository
				.getApprovedAndByDate(date, requestStatus);
		
		holidayRequests.forEach(model -> {
			int days = (int)model.getStartDate().until(model.getEndDate(), ChronoUnit.DAYS);
			model.setRequestedDays(days);
		});
		
		Collections.sort(holidayRequests, comparator);
		holidayRequests.forEach(holidayRequest -> {
			PrioritizedRequestModel model = new PrioritizedRequestModel();
			model.setRequestId(holidayRequest.getId());
			model.setRequestedDays(model.getRequestedDays());
			model.setTakenDays(model.getTakenDays());
			
			prioritizedRequests.add(model); 
		});
		
		return prioritizedRequests;
	}

	@Override
	public List<AlternativeDatesModel> getAlternativeDates(UUID requestId) {
		
		return null;
	}
}
