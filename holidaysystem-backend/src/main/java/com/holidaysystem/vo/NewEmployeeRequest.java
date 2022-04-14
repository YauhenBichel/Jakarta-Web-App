package com.holidaysystem.vo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;
import java.util.UUID;

/**
 * Request model for employee
 * @author yauhen bichel yb3129h@gre.ac.uk Student Id 001185491
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class NewEmployeeRequest implements Serializable {
     
    @NotBlank
    private String firstName;
    
    @NotBlank
    private String lastName;

    @NotBlank
    private UUID accountId;
    
    @NotBlank
    private String role;
    
    @NotBlank
    private String department;
    
    @PositiveOrZero
    private Integer years;
    
    
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
    public UUID getAccountId() {
    	return this.accountId;
    }
    public void setAccountId(UUID accountId) {
    	this.accountId = accountId;
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
}
