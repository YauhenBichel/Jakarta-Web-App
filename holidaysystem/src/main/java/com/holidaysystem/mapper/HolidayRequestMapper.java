package com.holidaysystem.mapper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;

import com.holidaysystem.entity.HolidayRequestEntity;
import com.holidaysystem.model.HolidayRequest;
import com.holidaysystem.model.HolidayResponse;

@ApplicationScoped
public class HolidayRequestMapper {
	
	private static final String BASE_PATTERN = "yyyy-MM-dd HH:mm:ss";
	private static final DateTimeFormatter FORMATTER = 
		    new DateTimeFormatterBuilder().appendPattern(BASE_PATTERN)
		        .appendFraction(ChronoField.NANO_OF_SECOND, 0, 9, true).toFormatter();
	
	public HolidayRequestEntity toEntity(UUID id, HolidayRequest request) {
		HolidayRequestEntity entity = new HolidayRequestEntity();
    	entity.setId(id);
    	entity.setEmployeeId(request.getEmployeeId());
    	entity.setStatus(request.getStatus());
    	entity.setStartDate(LocalDateTime.parse(request.getStartDate(), FORMATTER));
    	entity.setEndDate(LocalDateTime.parse(request.getEndDate(), FORMATTER));
    	entity.setCreated(LocalDateTime.now());
    	entity.setModified(LocalDateTime.now());
    	
    	return entity;
	}
	
	public HolidayRequestEntity toEntity(HolidayRequest updated, HolidayRequestEntity db) {
		HolidayRequestEntity entity = new HolidayRequestEntity();
    	entity.setId(db.getId());
    	entity.setEmployeeId(updated.getEmployeeId());
    	entity.setStatus(updated.getStatus());
    	entity.setStartDate(LocalDateTime.parse(updated.getStartDate(), FORMATTER));
    	entity.setEndDate(LocalDateTime.parse(updated.getEndDate(), FORMATTER));
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
