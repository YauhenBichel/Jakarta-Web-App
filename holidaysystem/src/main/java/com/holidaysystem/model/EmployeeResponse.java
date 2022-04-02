package com.holidaysystem.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class EmployeeResponse implements Serializable {
    
	private UUID id;
	
    @NotBlank
    private String firstName;
    
    @NotBlank
    private String lastName;

    @Email
    @NotBlank
    private String email;
    
    private String modified;
    
    private String created;
    
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
    public void setLastName(String lastName) {
    	this.lastName = lastName;
    }
    public String getEmail() {
    	return this.email;
    }
    public void setEmail(String email) {
    	this.email = email;
    }
    public String getCreated() {
    	return this.created;
    }
    public void setCreated(String created) {
    	this.created = created;
    }
    public String getModified() {
    	return this.modified;
    }
    public void setModified(String modified) {
    	this.modified = modified;
    }
}
