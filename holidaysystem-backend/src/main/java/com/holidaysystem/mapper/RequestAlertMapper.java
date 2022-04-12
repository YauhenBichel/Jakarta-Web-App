package com.holidaysystem.mapper;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;

import com.holidaysystem.entity.RequestAlertEntity;
import com.holidaysystem.message.HolidayRequestMessage;
import com.holidaysystem.vo.RequestAlertResponse;

/**
 * Request Alert mapper
 * @author yauhen bichel yb3129h@gre.ac.uk Student Id 001185491
 *
 */
@ApplicationScoped
public class RequestAlertMapper {
	
	public RequestAlertEntity toEntity(UUID alertId, HolidayRequestMessage holidayRequestMessage) {
		RequestAlertEntity entity = new RequestAlertEntity();
    	entity.setId(alertId);
    	entity.setRequestId(holidayRequestMessage.getId());
    	entity.setDate(LocalDateTime.now());
    	entity.setCreated(LocalDateTime.now());
    	entity.setModified(LocalDateTime.now());
    	
    	return entity;
	}

	public RequestAlertResponse toResponse(RequestAlertEntity entity) {
		RequestAlertResponse requestAlertResponse = new RequestAlertResponse();
		requestAlertResponse.setId(entity.getId());
		requestAlertResponse.setRequestId(entity.getRequestId());
		requestAlertResponse.setDate(entity.getDate().toString());
		
		return requestAlertResponse;
	}
}
