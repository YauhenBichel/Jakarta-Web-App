package com.holidaysystem.repository;

import java.util.UUID;
import java.util.List;
import com.holidaysystem.entity.HolidayDetailsEntity;

/**
 * 
 * @author yauhen bichel
 *
 */
public interface IHolidayDetailsRepository {
	
	HolidayDetailsEntity findById(UUID Id);
    
    boolean save(HolidayDetailsEntity holidayDetails);
    
    List<HolidayDetailsEntity> getHolidayDetails();
}
