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
package com.holidaysystem.mapper;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.holidaysystem.common.DateUtils;
import com.holidaysystem.entity.HolidayRequestEntity;
import com.holidaysystem.enumeration.HolidayRequestStatusEnum;
import com.holidaysystem.message.HolidayRequestMessage;
import com.holidaysystem.model.HolidayRequestModel;
import com.holidaysystem.vo.HolidayRequest;
import com.holidaysystem.vo.HolidayResponse;

/**
 * Holiday Request mapper
 * @author yauhen bichel yb3129h@gre.ac.uk Student Id 001185491
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
	
	public HolidayResponse toResponse(HolidayRequestModel model) {
		HolidayResponse holidayResponse = new HolidayResponse();
		holidayResponse.setId(model.getId());
		holidayResponse.setEmployeeId(model.getEmployeeId());
		holidayResponse.setStatus(model.getRequestStatus().name());
		holidayResponse.setStartDate(model.getStartDate().toString());
		holidayResponse.setEndDate(model.getEndDate().toString());
		holidayResponse.setTakenDays(model.getTakenDays());
		holidayResponse.setTotalDays(model.getTotalDays());
		holidayResponse.setRequestedDays(model.getRequestedDays());
		holidayResponse.setYears(model.getYears());
		
		return holidayResponse;
	}
	
	public HolidayResponse toResponse(HolidayRequestEntity entity) {
		HolidayResponse holidayResponse = new HolidayResponse();
		holidayResponse.setId(entity.getId());
		holidayResponse.setEmployeeId(entity.getEmployeeId());
		holidayResponse.setStatus(entity.getStatus());
		holidayResponse.setStartDate(entity.getStartDate().toString());
		holidayResponse.setEndDate(entity.getEndDate().toString());
		
		return holidayResponse;
	}
	
	public HolidayRequestModel toModel(HolidayRequestEntity entity) {
		HolidayRequestModel model = new HolidayRequestModel();
		model.setId(entity.getId());
		model.setEmployeeId(entity.getEmployeeId());
		model.setRequestStatus(HolidayRequestStatusEnum.valueOf(entity.getStatus()));
		model.setEndDate(entity.getEndDate());
		model.setStartDate(entity.getStartDate());
		
		return model;
	}
}
