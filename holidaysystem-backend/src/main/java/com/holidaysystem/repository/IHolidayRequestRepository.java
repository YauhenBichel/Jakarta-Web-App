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
package com.holidaysystem.repository;

import java.util.UUID;
import java.util.List;
import com.holidaysystem.entity.HolidayRequestEntity;
import com.holidaysystem.enumeration.HolidayRequestStatusEnum;
import com.holidaysystem.model.HolidayRequestModel;

/**
 * Interface for Holiday Request repository, which can be supported by
 * all implementations of the interface
 * @author yauhen bichel yb3129h@gre.ac.uk Student Id 001185491
 *
 */
public interface IHolidayRequestRepository {
	/**
	 * 
	 * @return
	 */
	List<HolidayRequestEntity> getHolidayRequests();
	/**
	 * 
	 * @return
	 */
	List<HolidayRequestModel> getHolidayRequestModels();
	/**
	 * 
	 * @param requestStatus
	 * @return
	 */
	List<HolidayRequestEntity> getHolidayRequestsByStatus(HolidayRequestStatusEnum requestStatus);
	/**
	 * 
	 * @param requestStatus
	 * @return
	 */
	List<HolidayRequestModel> getHolidayRequestModelsByStatus(HolidayRequestStatusEnum requestStatus);
	/**
	 * 
	 * @param holidayRequestId
	 * @return
	 */
	HolidayRequestEntity findById(UUID holidayRequestId);
	/**
	 * 
	 * @param holidayRequestEntity
	 * @return
	 */
	boolean save(HolidayRequestEntity holidayRequestEntity); 
	/**
	 * 
	 * @param id
	 * @param holidayRequestEntity
	 * @return
	 */
	HolidayRequestEntity update(UUID id, HolidayRequestEntity holidayRequestEntity); 
}
