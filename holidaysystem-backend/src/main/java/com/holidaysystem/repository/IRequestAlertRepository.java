package com.holidaysystem.repository;

import java.util.UUID;
import java.util.List;
import com.holidaysystem.entity.RequestAlertEntity;

/**
 * Interface for Request Alert repository, which can be supported by
 * all implementations of the interface
 * @author yauhen bichel yb3129h@gre.ac.uk Student Id 001185491
 *
 */
public interface IRequestAlertRepository {
	
	List<RequestAlertEntity> getRequestAlerts();
	
	RequestAlertEntity findById(UUID holidayRequestId);
	
	boolean save(RequestAlertEntity holidayRequestEntity);  
}
