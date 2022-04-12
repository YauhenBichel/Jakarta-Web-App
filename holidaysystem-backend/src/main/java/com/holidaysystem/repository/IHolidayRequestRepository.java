package com.holidaysystem.repository;

import java.util.UUID;
import java.util.List;
import com.holidaysystem.entity.HolidayRequestEntity;

/**
 * Interface for Holiday Request repository, which can be supported by
 * all implementations of the interface
 * @author yauhen bichel yb3129h@gre.ac.uk Student Id 001185491
 *
 */
public interface IHolidayRequestRepository {
	List<HolidayRequestEntity> getHolidayRequests();
	
	HolidayRequestEntity findById(UUID holidayRequestId);
	
	boolean save(HolidayRequestEntity holidayRequestEntity); 
	
	HolidayRequestEntity update(UUID id, HolidayRequestEntity holidayRequestEntity); 
}
