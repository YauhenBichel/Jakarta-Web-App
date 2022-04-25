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
	 * Finds employee entity by id
	 * @param employeeId UUID employee id
	 * @return employee entity
	 */
	EmployeeEntity findById(UUID employeeId);
    /**
     * Finds employee entity by email
     * @param email
     * @return employee entity
     */
    EmployeeEntity findByEmail(String email);
    /**
     * Adds a new employee
     * @param employeeEntity employee entity
     * @return true is added, false is failed
     */
    boolean save(EmployeeEntity employeeEntity);
    /**
     * Gets all employee entities
     * @return list of employee entities
     */
    List<EmployeeEntity> getEmployees();
    /**
     * Gets all employee models
     * @return list of employee models
     */
    List<EmployeeModel> getEmployeeModels();
    /**
     * Gets employees from specific department
     * @param department department
     * @return list of employee models
     */
    List<EmployeeModel> getEmployeeModelsByDepartmentId(String department);
    /**
     * Gets employees on holidays on specific date
     * @param date the date
     * @return list of employee models on holidays
     */
    List<EmployeeModel> getEmployeeModelsOnHolidaysByDate(LocalDateTime date);
    /**
     * Gets employees on holidays or on duty on specific date
     * @param date the date
     * @param holidayStatus the status of HolidayStatusEnum
     * @return list of employee models
     */
    List<EmployeeModel> getEmployeeModelsByDateAndHolidayStatus(LocalDateTime date, HolidayStatusEnum holidayStatus);
}
