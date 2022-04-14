package com.holidaysystem.service;

import java.util.List;
import java.util.UUID;

import com.holidaysystem.entity.HolidayRequestEntity;
import com.holidaysystem.enumeration.HolidayRequestStatusEnum;
import com.holidaysystem.model.HolidayRequestModel;
import com.holidaysystem.vo.HolidayRequest;

/**
 * Interface for HolidayRequest service provides user register and login
 * @author yauhen bichel yb3129h@gre.ac.uk Student Id 001185491
 *
 */
public interface IHolidayRequestService {
	List<HolidayRequestModel> getHolidayRequests();
	List<HolidayRequestModel> getHolidayRequestsByStatus(HolidayRequestStatusEnum status);
	HolidayRequestModel fetchModelById(UUID requestId);
	HolidayRequestEntity findEntityById(UUID requestId);
	void addHolidayRequest(UUID requestId, HolidayRequest holidayRequest);
	HolidayRequestEntity update(UUID requestId, HolidayRequest holidayRequest);
	boolean validate(UUID requestId);
}
