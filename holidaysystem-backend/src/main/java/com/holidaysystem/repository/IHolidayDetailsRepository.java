package com.holidaysystem.repository;

import java.util.UUID;
import java.util.List;
import com.holidaysystem.entity.HolidayDetailsEntity;

/**
 * Interface for Holiday Details repository, which can be supported by
 * all implementations of the interface
 * @author yauhen bichel yb3129h@gre.ac.uk Student Id 001185491
 *
 */
public interface IHolidayDetailsRepository {
	
	HolidayDetailsEntity findById(UUID Id);
	
	HolidayDetailsEntity findByEmployeeId(UUID employeeId);
    
    boolean save(HolidayDetailsEntity holidayDetails);
    
    List<HolidayDetailsEntity> getHolidayDetails();
}
