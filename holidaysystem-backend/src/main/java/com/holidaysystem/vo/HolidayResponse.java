package com.holidaysystem.vo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;
import java.util.UUID;

/**
 * Response model for holiday request
 * @author yauhen bichel yb3129h@gre.ac.uk Student Id 001185491
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class HolidayResponse implements Serializable {
    
	@NotBlank
    private UUID id;
	
    @NotBlank
    private UUID employeeId;

    @NotBlank
    private String status;
    
    private Integer requestedDays;
    
    @NotBlank
    private String startDate;
    
    @NotBlank
    private String endDate;
    
    private Integer years;
    
    private Integer totalDays;
    
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
    public String getStartDate() {
    	return this.startDate;
    }
    public void setStartDate(String startDate) {
    	this.startDate = startDate;
    }
    public void setEndDate(String endDate) {
    	this.endDate = endDate;
    }
    public String getEndDate() {
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
