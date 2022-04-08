package com.holidaysystem.mapper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.holidaysystem.DateUtils;
import com.holidaysystem.entity.HolidayRequestEntity;
import com.holidaysystem.entity.RequestAlertEntity;
import com.holidaysystem.message.HolidayRequestMessage;
import com.holidaysystem.vo.HolidayRequest;
import com.holidaysystem.vo.HolidayResponse;
import com.holidaysystem.vo.RequestAlertResponse;

/**
 * 
 * @author yauhen bichel
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
