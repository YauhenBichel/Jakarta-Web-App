package com.holidaysystem.message;

import java.time.LocalDateTime;
import java.util.UUID;

public class HolidayRequestMessage {
	private static final long serialVersionUID = 1L;
	
    private UUID id;
    private UUID employeeId;
	private LocalDateTime startDate;
	private LocalDateTime endDate;
	private String status;

    public void setId(UUID id) {this.id = id; }
    public UUID getId() {return this.id;}
    public void setEmployeeId(UUID employeeId) {this.employeeId = employeeId; }
    public UUID getEmployeeId() {return this.employeeId;}
    public void setStatus(String status) {this.status = status; }
    public String getStatus() {return this.status;}
    public void setStartDate(LocalDateTime startDate) {this.startDate = startDate; }
    public LocalDateTime getStartDate() {return this.startDate;}
    public void setEndDate(LocalDateTime endDate) {this.endDate = endDate; }
    public LocalDateTime getEndDate() {return this.endDate;}
}
