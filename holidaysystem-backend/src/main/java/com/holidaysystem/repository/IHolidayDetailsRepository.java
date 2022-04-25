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
import java.time.LocalDateTime;
import java.util.List;
import com.holidaysystem.entity.HolidayDetailsEntity;
import com.holidaysystem.enumeration.HolidayRequestStatusEnum;
import com.holidaysystem.model.HolidayRequestModel;

/**
 * Interface for Holiday Details repository, which can be supported by
 * all implementations of the interface
 * @author yauhen bichel yb3129h@gre.ac.uk Student Id 001185491
 *
 */
public interface IHolidayDetailsRepository {
	/**
	 * Finds holiday details entity by id
	 * @param Id UUID of holiday details entity
	 * @return HolidayDetailsEntity entity
	 */
	HolidayDetailsEntity findById(UUID Id);
	/**
	 * Finds employee by employee Id
	 * @param employeeId
	 * @return HolidayDetailsEntity entity
	 */
	HolidayDetailsEntity findByEmployeeId(UUID employeeId);
    /**
     * Adds a new holiday detail entity
     * @param holidayDetails
     * @return true is saved, false is failed
     */
    boolean save(HolidayDetailsEntity holidayDetails);
    /**
     * Fetches all holiday detail entities
     * @return list of HolidayDetailsEntity entities
     */
    List<HolidayDetailsEntity> getHolidayDetails();
    /**
     * Fetches HolidayRequest models
     * @param date LocalDateTime
     * @param requestStatus HolidayRequestStatusEnum enumeration
     * @return list of HolidayRequestModel models
     */
    List<HolidayRequestModel> getApprovedAndByDate(LocalDateTime date, HolidayRequestStatusEnum requestStatus);
}
