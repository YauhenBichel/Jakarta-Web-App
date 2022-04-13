package com.holidaysystem.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;

import com.holidaysystem.enumeration.DepartmentEnum;
import com.holidaysystem.enumeration.EmployeeRoleEnum;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;
import java.util.UUID;

/**
 * Employee model includes data from different entities
 * @author yauhen bichel yb3129h@gre.ac.uk Student Id 001185491
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class EmployeeModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotBlank
    private UUID id;
	
    private String email;
	
    @NotBlank
    private String firstName;
    
    @NotBlank
    private String lastName;

    @NotBlank
    private UUID accountId;
    
    @NotBlank
    private EmployeeRoleEnum role;
    
    @NotBlank
    private DepartmentEnum department;
    
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
    public String getFirstName() {
    	return this.firstName;
    }
    public void setFirstName(String firstName) {
    	this.firstName = firstName;
    }
    public String getLastName() {
    	return this.lastName;
    }
    public void setEmail(String email) {
    	this.email = email;
    }
    public String getEmail() {
    	return this.email;
    }
    public void setLastName(String lastName) {
    	this.lastName = lastName;
    }
    public UUID getAccountId() {
    	return this.accountId;
    }
    public void setAccountId(UUID accountId) {
    	this.accountId = accountId;
    }
    public EmployeeRoleEnum getRole() {
    	return this.role;
    }
    public void setRole(EmployeeRoleEnum role) {
    	this.role = role;
    }
    public DepartmentEnum getDepartment() {
    	return this.department;
    }
    public void setDepartment(DepartmentEnum department) {
    	this.department = department;
    }
    public void setYears(Integer years) {this.years = years; }
    public Integer getYears() {return this.years;}
    public void setTotalDays(Integer totalDays) {this.totalDays = totalDays; }
    public Integer getTotalDays() {return this.totalDays;}
    public void setTakenDays(Integer takenDays) {this.takenDays = takenDays; }
    public Integer getTakenDays() {return this.takenDays;}
}
