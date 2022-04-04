package com.holidaysystem.repository;

import java.util.UUID;
import java.util.List;
import com.holidaysystem.entity.HolidayRequestEntity;

public interface IHolidayRequestRepository {
	List<HolidayRequestEntity> getHolidayRequests();
	HolidayRequestEntity findById(UUID holidayRequestId);
	boolean save(HolidayRequestEntity holidayRequestEntity); 
	HolidayRequestEntity update(UUID id, HolidayRequestEntity holidayRequestEntity); 
  }
