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

import com.holidaysystem.enumeration.HolidayStatusEnum;
import com.holidaysystem.model.EmployeeModel;
import com.holidaysystem.vo.NewEmployeeRequest;

/**
 * Interface for Employee service provides employee details
 * @author yauhen bichel yb3129h@gre.ac.uk Student Id 001185491
 *
 */
public interface IEmployeeService {
	/**
	 * Gets employees from all departments
	 * @return list of EmployeeModel models
	 */
    List<EmployeeModel> getEmployees();
    /**
     * Gets employees from all departments
     * @param date
     * @return
     */
    List<EmployeeModel> getEmployeesByDate(LocalDateTime date);
    /**
     * 
     * @param date
     * @param holidayStatus
     * @return
     */
    List<EmployeeModel> getEmployeesByDateAndHolidayStatus(LocalDateTime date, HolidayStatusEnum holidayStatus);
    /**
     * 
     * @param employeeId
     * @return
     */
    EmployeeModel findById(UUID employeeId);
    /**
     * 
     * @param employeeRequest
     * @return
     */
    EmployeeModel create(NewEmployeeRequest employeeRequest);
}
