package com.holidaysystem.service;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.transaction.Transactional;

import com.holidaysystem.entity.AccountEntity;
import com.holidaysystem.entity.AuthorizationRoleEntity;
import com.holidaysystem.entity.HolidayRequestEntity;
import com.holidaysystem.model.AccountDetailsModel;
import com.holidaysystem.enumeration.AuthorizationRoleEnum;
import com.holidaysystem.enumeration.HolidayRequestStatusEnum;
import com.holidaysystem.mapper.AccountMapper;
import com.holidaysystem.mapper.HolidayRequestMapper;
import com.holidaysystem.message.HolidayRequestMQProducer;
import com.holidaysystem.repository.IAccountRepository;
import com.holidaysystem.repository.IAuthorizationRoleRepository;
import com.holidaysystem.repository.IHolidayRequestRepository;
import com.holidaysystem.vo.HolidayResponse;
import com.holidaysystem.vo.RegistrationRequest;

import javax.inject.Inject;

/**
 * HolidayRequest service provides user register and login
 * @author yauhen bichel yb3129h@gre.ac.uk Student Id 001185491
 *
 */
@ApplicationScoped
@Default
public class HolidayRequestService implements IHolidayRequestService {
	
	@Inject
    private IHolidayRequestRepository holidayRequestRepository;
    @Inject
    private HolidayRequestMapper holidayRequestMapper;
    @Inject
    private HolidayRequestMQProducer holidayRequestMQProducer;
	
	@Transactional
	public List<HolidayResponse> getHolidayRequests() {
		List<HolidayRequestEntity> entities = holidayRequestRepository.getHolidayRequests();
		
    	List<HolidayResponse> holidayRequestResponses = new ArrayList<>();
    	for(HolidayRequestEntity entity: entities) {
    		HolidayResponse holidayResponse = holidayRequestMapper.toResponse(entity);
    		holidayRequestResponses.add(holidayResponse);
    	}
    	
    	return holidayRequestResponses;
	}
	
	@Transactional
	public List<HolidayResponse> getHolidayRequestsByStatus(HolidayRequestStatusEnum status) {
		List<HolidayRequestEntity> entities = holidayRequestRepository.getHolidayRequestsByStatus(status);
    	
		List<HolidayResponse> holidayRequestResponses = new ArrayList<>();
    	
    	for(HolidayRequestEntity entity: entities) {
    		HolidayResponse holidayResponse = holidayRequestMapper.toResponse(entity);
    		holidayRequestResponses.add(holidayResponse);
    	}
    	
    	return holidayRequestResponses;
	}
	
	
}
