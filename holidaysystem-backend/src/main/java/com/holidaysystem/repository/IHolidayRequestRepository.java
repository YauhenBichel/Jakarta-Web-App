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
	List<HolidayRequestEntity> getHolidayRequests();
	List<HolidayRequestModel> getHolidayRequestModels();
	List<HolidayRequestModel> getHolidayRequestModels(int offset, int limit);
	List<HolidayRequestEntity> getHolidayRequestsByStatus(HolidayRequestStatusEnum requestStatus);
	List<HolidayRequestModel> getHolidayRequestModelsByStatus(HolidayRequestStatusEnum requestStatus);
	HolidayRequestEntity findById(UUID holidayRequestId);
	boolean save(HolidayRequestEntity holidayRequestEntity); 
	HolidayRequestEntity update(UUID id, HolidayRequestEntity holidayRequestEntity); 
}
