package com.holidaysystem.service;

import java.util.List;

import com.holidaysystem.enumeration.HolidayRequestStatusEnum;
import com.holidaysystem.vo.HolidayResponse;

/**
 * Interface for HolidayRequest service provides user register and login
 * @author yauhen bichel yb3129h@gre.ac.uk Student Id 001185491
 *
 */
public interface IHolidayRequestService {
	List<HolidayResponse> getHolidayRequests();
	List<HolidayResponse> getHolidayRequestsByStatus(HolidayRequestStatusEnum status);
	
	HolidayRequestModel findById(UUID requestId);
}
