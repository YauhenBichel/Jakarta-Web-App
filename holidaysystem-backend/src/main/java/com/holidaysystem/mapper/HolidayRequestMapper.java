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
import com.holidaysystem.message.HolidayRequestMessage;
import com.holidaysystem.vo.HolidayRequest;
import com.holidaysystem.vo.HolidayResponse;

/**
 * 
 * @author yauhen bichel
 *
 */
@ApplicationScoped
public class HolidayRequestMapper {
	public HolidayRequestEntity toEntity(UUID id, HolidayRequest request) {
		HolidayRequestEntity entity = new HolidayRequestEntity();
    	entity.setId(id);
    	entity.setEmployeeId(request.getEmployeeId());
    	entity.setStatus(request.getStatus());
    	entity.setStartDate(LocalDateTime.parse(request.getStartDate(), DateUtils.FORMATTER));
    	entity.setEndDate(LocalDateTime.parse(request.getEndDate(), DateUtils.FORMATTER));
    	entity.setCreated(LocalDateTime.now());
    	entity.setModified(LocalDateTime.now());
    	
    	return entity;
	}
	
	public HolidayRequestEntity toEntity(HolidayRequestMessage holidayRequestMessage) {
		HolidayRequestEntity entity = new HolidayRequestEntity();
    	entity.setId(holidayRequestMessage.getId());
    	entity.setEmployeeId(holidayRequestMessage.getEmployeeId());
    	entity.setStatus(holidayRequestMessage.getStatus());
    	entity.setStartDate(LocalDateTime.parse(holidayRequestMessage.getStartDate(), DateUtils.FORMATTER));
    	entity.setEndDate(LocalDateTime.parse(holidayRequestMessage.getEndDate(), DateUtils.FORMATTER));
    	entity.setCreated(LocalDateTime.now());
    	entity.setModified(LocalDateTime.now());
    	
    	return entity;
	}
	
	public HolidayRequestMessage toMessage(UUID id, HolidayRequest request) {
		HolidayRequestMessage message = new HolidayRequestMessage();
		message.setId(id);
		message.setEmployeeId(request.getEmployeeId());
		message.setStatus(request.getStatus());
		message.setStartDate(request.getStartDate());
		message.setEndDate(request.getEndDate());
    	
    	return message;
	}
	
	public HolidayRequestMessage toMessage(String jsonMessage) {
    	
    	try {
    		ObjectMapper objectMapper = new ObjectMapper();
    		HolidayRequestMessage message = objectMapper.readValue(jsonMessage, HolidayRequestMessage.class);
    		
    	    return message;
    	}
    	catch (JsonGenerationException | JsonMappingException  e) {
    	    e.printStackTrace();
    	}
    	catch(JsonProcessingException e) {
    		e.printStackTrace();
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    	return null;
	}
	
	public String toJson(UUID id, HolidayRequest request) {
		HolidayRequestMessage message = toMessage(id, request);
    	
    	
    	try {
    		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        	String json = ow.writeValueAsString(message);
    		
    	    return json;
    	}
    	catch (JsonGenerationException | JsonMappingException  e) {
    	    e.printStackTrace();
    	}
    	catch(JsonProcessingException e) {
    		e.printStackTrace();
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    	return null;
	}
	
	public HolidayRequestEntity toEntity(HolidayRequest updated, HolidayRequestEntity db) {
		HolidayRequestEntity entity = new HolidayRequestEntity();
    	entity.setId(db.getId());
    	entity.setEmployeeId(updated.getEmployeeId());
    	entity.setStatus(updated.getStatus());
    	entity.setStartDate(LocalDateTime.parse(updated.getStartDate(), DateUtils.FORMATTER));
    	entity.setEndDate(LocalDateTime.parse(updated.getEndDate(), DateUtils.FORMATTER));
    	entity.setCreated(db.getCreated());
    	entity.setModified(LocalDateTime.now());
    	
    	return entity;
	}
	
	public HolidayResponse toResponse(HolidayRequestEntity entity) {
		HolidayResponse holidayRequest = new HolidayResponse();
		holidayRequest.setId(entity.getId());
		holidayRequest.setEmployeeId(entity.getEmployeeId());
		holidayRequest.setStatus(entity.getStatus());
		holidayRequest.setStartDate(entity.getStartDate().toString());
		holidayRequest.setEndDate(entity.getEndDate().toString());
		holidayRequest.setCreated(entity.getCreated().toString());
		holidayRequest.setModified(entity.getModified().toString());
		
		return holidayRequest;
	}
}
