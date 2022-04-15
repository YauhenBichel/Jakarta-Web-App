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
	/**
	 * 
	 * @return
	 */
	List<HolidayRequestModel> getHolidayRequests();
	/**
	 * 
	 * @param status
	 * @return
	 */
	List<HolidayRequestModel> getHolidayRequestsByStatus(HolidayRequestStatusEnum status);
	/**
	 * 
	 * @param requestId
	 * @return
	 */
	HolidayRequestModel fetchModelById(UUID requestId);
	/**
	 * 
	 * @param requestId
	 * @return
	 */
	HolidayRequestEntity findEntityById(UUID requestId);
	/**
	 * 
	 * @param requestId
	 * @param holidayRequest
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
	 * 
	 * @param requestId
	 * @return
	 */
	boolean validate(UUID requestId);
}
