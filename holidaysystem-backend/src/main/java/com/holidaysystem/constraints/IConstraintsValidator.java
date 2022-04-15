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
package com.holidaysystem.constraints;

import java.util.List;

import com.holidaysystem.model.EmployeeModel;

/**
 * 
 * @author yauhen bichel yb3129h@gre.ac.uk Student Id 001185491
 *
 */
public interface IConstraintsValidator {
	/**
	 * 
	 * @param epmployees
	 * @return
	 */
	boolean hasHeadOrDeputyHead(List<EmployeeModel> epmployees);
	/**
	 * 
	 * @param epmployees
	 * @return
	 */
	boolean hasManagerAndSeniorMemeber(List<EmployeeModel> epmployees);
	/**
	 * 
	 * @param epmployees
	 * @param percentOnDuty
	 * @return
	 */
	boolean hasSpecificPercentMemebersOnDuty(List<EmployeeModel> epmployees, Integer percentOnDuty);
}
