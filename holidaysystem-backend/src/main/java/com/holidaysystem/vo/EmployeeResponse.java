package com.holidaysystem.vo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import com.holidaysystem.enumeration.HolidayStatusEnum;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;
import java.util.UUID;

/**
 * Response model for employee
 * @author yauhen bichel yb3129h@gre.ac.uk Student Id 001185491
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class EmployeeResponse implements Serializable {
    
	@NotBlank
	private UUID id;
	
	@NotBlank
	private UUID accountId;
	
    private String email;
	
    @NotBlank
    private String firstName;
    
    @NotBlank
    private String lastName;

    @NotBlank
    private String role;
    
    @NotBlank
    private String department;
    
    @PositiveOrZero
    private Integer years;
    
    @PositiveOrZero
    private Integer totalDays;
    
    @PositiveOrZero
    private Integer takenDays;
    
    @NotBlank
    private String holidayStatus;
    
    
    public UUID getId() {
    	return this.id;
    }
    public void setId(UUID id) {
    	this.id = id;
    }
    public UUID getAccountId() {
    	return this.accountId;
    }
    public void setAccountId(UUID accountId) {
    	this.accountId = accountId;
    }
    public String getEmail() {
    	return this.email;
    }
    public void setEmail(String email) {
    	this.email = email;
    }
    public String getFirstName() {
    	return this.firstName;
    }
    public void setFirstName(String firstName) {
    	this.firstName = firstName;
    }
    public String getLastName() {
    	return this.lastName;
    }
    public void setLastName(String lastName) {
    	this.lastName = lastName;
    }
    public String getRole() {
    	return this.role;
    }
    public void setRole(String role) {
    	this.role = role;
    }
    public String getDepartment() {
    	return this.department;
    }
    public void setDepartment(String department) {
    	this.department = department;
    }
    public void setYears(Integer years) {this.years = years; }
    public Integer getYears() {return this.years;}
    public void setTotalDays(Integer totalDays) {this.totalDays = totalDays; }
    public Integer getTotalDays() {return this.totalDays;}
    public void setTakenDays(Integer takenDays) {this.takenDays = takenDays; }
    public Integer getTakenDays() {return this.takenDays;}
    public String getHolidayStatus() {
    	return this.holidayStatus;
    }
    public void setHolidayStatus(String holidayStatus) {
    	this.holidayStatus = holidayStatus;
    }
}
