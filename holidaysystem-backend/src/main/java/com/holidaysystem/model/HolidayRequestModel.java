package com.holidaysystem.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Holiday Request model includes data from different entities
 * @author yauhen bichel yb3129h@gre.ac.uk Student Id 001185491
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class HolidayRequestModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotBlank
    private UUID id;
	
    @NotBlank
    private UUID employeeId;

    @NotBlank
    private String status;
    
    @PositiveOrZero
    private Integer requestedDays;
    
    @NotNull
    private LocalDateTime startDate;
    
    @NotNull
    private LocalDateTime endDate;
    
    @PositiveOrZero
    private Integer years;
    
    @PositiveOrZero
    private Integer totalDays;
    
    @PositiveOrZero
    private Integer takenDays;
    
    public UUID getId() {
    	return this.id;
    }
    public void setId(UUID id) {
    	this.id = id;
    }
    public UUID getEmployeeId() {
    	return this.employeeId;
    }
    public void setEmployeeId(UUID employeeId) {
    	this.employeeId = employeeId;
    }
    public String getStatus() {
    	return this.status;
    }
    public void setStatus(String status) {
    	this.status = status;
    }
    public LocalDateTime getStartDate() {
    	return this.startDate;
    }
    public void setStartDate(LocalDateTime startDate) {
    	this.startDate = startDate;
    }
    public void setEndDate(LocalDateTime endDate) {
    	this.endDate = endDate;
    }
    public LocalDateTime getEndDate() {
    	return this.endDate;
    }
    public void setYears(Integer years) {this.years = years; }
    public Integer getYears() {return this.years;}
    public void setTotalDays(Integer totalDays) {this.totalDays = totalDays; }
    public Integer getTotalDays() {return this.totalDays;}
    public void setTakenDays(Integer takenDays) {this.takenDays = takenDays; }
    public Integer getTakenDays() {return this.takenDays;}
    public void setRequestedDays(Integer requestedDays) {this.requestedDays = requestedDays; }
    public Integer getRequestedDays() {return this.requestedDays;}
}
