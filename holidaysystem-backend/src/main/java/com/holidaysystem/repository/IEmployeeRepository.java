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
import com.holidaysystem.entity.EmployeeEntity;
import com.holidaysystem.enumeration.HolidayStatusEnum;
import com.holidaysystem.model.EmployeeModel;

/**
 * Interface for Employee repository, which can be supported by
 * all implementations of the interface
 * @author yauhen bichel yb3129h@gre.ac.uk Student Id 001185491
 *
 */
public interface IEmployeeRepository {
	/**
	 * 
	 * @param userId
	 * @return
	 */
	EmployeeEntity findById(UUID userId);
    /**
     * 
     * @param email
     * @return
     */
    EmployeeEntity findByEmail(String email);
    /**
     * 
     * @param user
     * @return
     */
    boolean save(EmployeeEntity user);
    /**
     * 
     * @return
     */
    List<EmployeeEntity> getEmployees();
    /**
     * 
     * @return
     */
    List<EmployeeModel> getEmployeeModels();
    /**
     * 
     * @param department
     * @return
     */
    List<EmployeeModel> getEmployeeModelsByDepartmentId(String department);
    /**
     * 
     * @param date
     * @return
     */
    List<EmployeeModel> getEmployeeModelsByDate(LocalDateTime date);
    /**
     * 
     * @param date
     * @param holidayStatus
     * @return
     */
    List<EmployeeModel> getEmployeeModelsByDateAndHolidayStatus(LocalDateTime date, HolidayStatusEnum holidayStatus);
}
