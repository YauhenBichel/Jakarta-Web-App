package com.holidaysystem.vo;

import java.io.Serializable;
import java.util.UUID;

import javax.validation.constraints.NotBlank;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class RequestAlertResponse implements Serializable {
	
	@NotBlank
    private UUID id;
	
	@NotBlank
    private UUID requestId;
    
    @NotBlank
    private String date;
    
    public UUID getId() {
    	return this.id;
    }
    public void setId(UUID id) {
    	this.id = id;
    }
    public UUID getRequestId() {
    	return this.requestId;
    }
    public void setRequestId(UUID requestId) {
    	this.requestId = requestId;
    }
    public String getDate() {
    	return this.date;
    }
    public void setDate(String date) {
    	this.date = date;
    }
}
