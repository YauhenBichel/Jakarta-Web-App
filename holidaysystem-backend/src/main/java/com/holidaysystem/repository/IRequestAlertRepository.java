package com.holidaysystem.repository;

import java.util.UUID;
import java.util.List;
import com.holidaysystem.entity.RequestAlertEntity;

public interface IRequestAlertRepository {
	List<RequestAlertEntity> getRequestAlerts();
	RequestAlertEntity findById(UUID holidayRequestId);
	boolean save(RequestAlertEntity holidayRequestEntity);  
  }
