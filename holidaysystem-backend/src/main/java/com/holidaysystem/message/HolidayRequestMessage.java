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
package com.holidaysystem.message;

import java.util.UUID;

/**
 * Model of the message in message system
 * @author yauhen bichel yb3129h@gre.ac.uk Student Id 001185491
 *
 */
public class HolidayRequestMessage {
	private static final long serialVersionUID = 1L;
	
    private UUID id;
    private UUID employeeId;
	private String startDate;
	private String endDate;
	private String status;

    public void setId(UUID id) {this.id = id; }
    public UUID getId() {return this.id;}
    public void setEmployeeId(UUID employeeId) {this.employeeId = employeeId; }
    public UUID getEmployeeId() {return this.employeeId;}
    public void setStatus(String status) {this.status = status; }
    public String getStatus() {return this.status;}
    public void setStartDate(String startDate) {this.startDate = startDate; }
    public String getStartDate() {return this.startDate;}
    public void setEndDate(String endDate) {this.endDate = endDate; }
    public String getEndDate() {return this.endDate;}
}
