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
package com.holidaysystem.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.holidaysystem.entity.HolidayRequestEntity;
import com.holidaysystem.enumeration.HolidayRequestStatusEnum;
import com.holidaysystem.model.AlternativeDatesModel;
import com.holidaysystem.model.HolidayRequestModel;
import com.holidaysystem.model.PrioritizedRequestModel;
import com.holidaysystem.vo.HolidayRequest;

/**
 * Interface for HolidayRequest service provides user register and login
 * @author yauhen bichel yb3129h@gre.ac.uk Student Id 001185491
 */
public interface IHolidayRequestService {
	/**
	 * Gets a list of holiday request models 
	 * limit 100
	 * @return list of HolidayRequestModel
	 */
	List<HolidayRequestModel> getHolidayRequests();
	/**
	 * Gets a list of holiday request models sorted by created
	 * @param offset skip records
	 * @param limit size of the page
	 * @return list of HolidayRequestModel
	 */
	List<HolidayRequestModel> getHolidayRequests(int offset, int limit);
	/**
	 * Gets a list of holiday request models by request status
	 * @param status HolidayRequestStatusEnum status
	 * @return list of HolidayRequestModel
	 */
	List<HolidayRequestModel> getHolidayRequestsByStatus(HolidayRequestStatusEnum status);
	/**
	 * Finds holiday request model, which extended data, by request id
	 * @param requestId UUID request Id
	 * @return holiday request model
	 */
	HolidayRequestModel fetchModelById(UUID requestId);
	/**
	 * Finds entity by request Id
	 * @param requestId UUID request id
	 * @return holiday request entity
	 */
	HolidayRequestEntity findEntityById(UUID requestId);
	/**
	 * Adds a new holiday request
	 * @param requestId UUID holiday request
	 * @param holidayRequest the model request
	 */
	void addHolidayRequest(UUID requestId, HolidayRequest holidayRequest);
	/**
	 * 
	 * @param requestId
	 * @param holidayRequest
	 * @return
	 */
	HolidayRequestEntity update(UUID requestId, HolidayRequest holidayRequest);
	/**
	 * Validates the request for supporting constraints
	 * @param requestId UUID of the request
	 * @return boolean: true is valid, false is not valid
	 */
	boolean validate(UUID requestId);
	/**
	 * Sorts requests by taken days and requested days
	 * @param date
	 * @param requestStatus
	 * @return list of prioritized requests 
	 */
	List<PrioritizedRequestModel> getPrioritizedHolidayRequests(LocalDateTime date, HolidayRequestStatusEnum requestStatus);
	/**
	 * Calculates alternative dates for broken requests
	 * @param requestId UUID of the broken request
	 * @return list of alternative dates
	 */
	List<AlternativeDatesModel> getAlternativeDates(UUID requestId);
}
