package com.holidaysystem.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.FutureOrPresent;


/**
 * HolidayRequest entity
 * @author yauhen bichel
 *
 */
@Entity
@Table(name = "holiday-request", schema = "public")
public class HolidayRequestEntity extends AuditEntity {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;
	
	@Column(name = "employeeid")
    private UUID employeeId;
	
	@FutureOrPresent
	@Column(name = "startdate")
	private LocalDateTime startDate;
	
	@FutureOrPresent
	@Column(name = "enddate")
	private LocalDateTime endDate;
	
	@Column(name = "status")
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
