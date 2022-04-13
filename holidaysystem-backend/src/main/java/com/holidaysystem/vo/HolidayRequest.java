package com.holidaysystem.vo;

import javax.validation.constraints.NotBlank;

import com.holidaysystem.validation.ValueOfEnum;
import com.holidaysystem.enumeration.HolidayRequestStatusEnum;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;
import java.util.UUID;

/**
 * Request model for holiday request
 * @author yauhen bichel yb3129h@gre.ac.uk Student Id 001185491
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class HolidayRequest implements Serializable {
     
    @NotBlank
    private UUID employeeId;

    @NotBlank
    @ValueOfEnum(enumClass = HolidayRequestStatusEnum.class)
    private String status;
    
    @NotBlank
    private String startDate;
    
    @NotBlank
    private String endDate;
    
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
    public void setEndDate(String endDate) {
    	this.endDate = endDate;
    }
    public String getEndDate() {
    	return this.endDate;
    }
    public void setStartDate(String startDate) {
    	this.startDate = startDate;
    }
    
}
