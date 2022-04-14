package com.holidaysystem.vo;

import javax.validation.constraints.NotBlank;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;

/**
 * Request model for employees by date
 * @author yauhen bichel yb3129h@gre.ac.uk Student Id 001185491
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class FindEmployeesByDateRequest implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@NotBlank
    private String date;
    
    public void setDate(String date) {
    	this.date = date;
    }
    public String getDate() {return this.date; }
}
