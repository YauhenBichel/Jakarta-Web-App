package com.holidaysystem.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.transaction.Transactional;

import org.jboss.logging.Logger;

import com.holidaysystem.Constants;
import com.holidaysystem.constraints.IConstraintsValidator;
import com.holidaysystem.entity.EmployeeEntity;
import com.holidaysystem.entity.HolidayRequestEntity;
import com.holidaysystem.model.EmployeeModel;
import com.holidaysystem.model.HolidayRequestModel;
import com.holidaysystem.enumeration.HolidayRequestStatusEnum;
import com.holidaysystem.mapper.HolidayRequestMapper;
import com.holidaysystem.message.HolidayRequestMQProducer;
import com.holidaysystem.repository.IEmployeeRepository;
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
    private IEmployeeRepository employeeRepository;
    @Inject
    private HolidayRequestMapper holidayRequestMapper;
    @Inject
    private HolidayRequestMQProducer holidayRequestMQProducer;
    @Inject
    private IConstraintsValidator constraintValidator;
	
	@Transactional
	public List<HolidayRequestModel> getHolidayRequests() {
		List<HolidayRequestModel> models = holidayRequestRepository.getHolidayRequestModels();
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
    	return null;
	}
	
	@Transactional
	public boolean validate(UUID requestId) {
		
		HolidayRequestEntity dbHolidayRequestEntity = this.findEntityById(requestId);
		if(dbHolidayRequestEntity == null) {
			logger.error("holiday request not found");
			throw new RuntimeException("holiday request not found");
		}
		
		EmployeeEntity dbEmployeeEntity = employeeRepository.findById(dbHolidayRequestEntity.getEmployeeId());
		if(dbEmployeeEntity == null) {
			logger.error("employee not found");
			throw new RuntimeException("employee not found");
		}
		
		List<EmployeeModel> employeeModels = employeeRepository.getEmployeeModelsByDepartmentId(dbEmployeeEntity.getDepartment());
		
		LocalDateTime now = LocalDateTime.now();
		
		if(now.isAfter(LocalDateTime.of(LocalDate.of(LocalDate.now().getYear(), 12, 22), null)) && 
				now.isBefore(LocalDateTime.of(LocalDate.of(LocalDate.now().getYear() + 1, 01, 3), null))) {
		
			final int percentOnDuty = LocalDate.now().getMonthValue() == 8 ? 
					Constants.PERCENT_MEMBERS_AUGUST_ON_DUTY:
				Constants.PERCENT_MEMBERS_ON_DUTY;
			
		return constraintValidator.hasHeadOrDeputyHead(employeeModels) &&
				constraintValidator.hasManagerAndSeniorMemeber(employeeModels) &&
				constraintValidator.hasSpecificPercentMemebersOnDuty(employeeModels, percentOnDuty);
		}
		
		return true;
	}
}
