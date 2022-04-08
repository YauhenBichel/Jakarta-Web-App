package com.holidaysystem.message;

import java.util.UUID;

/**
 * 
 * @author yauhen bichel
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
