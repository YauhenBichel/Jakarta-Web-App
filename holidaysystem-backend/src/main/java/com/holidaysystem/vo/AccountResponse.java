package com.holidaysystem.vo;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;
import java.util.UUID;

/**
 * Response model for account
 * @author yauhen bichel yb3129h@gre.ac.uk Student Id 001185491
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class AccountResponse implements Serializable {
    
	@NotBlank
	private UUID id;
	
	@Email
	private String email;
	
	@NotBlank
    private String authRole;

	@PastOrPresent
	private String modified;
    
	@PastOrPresent
    private String created;
    
    public UUID getId() {
    	return this.id;
    }
    public void setId(UUID id) {
    	this.id = id;
    }
    public String getEmail() {
    	return this.email;
    }
    public void setEmail(String email) {
    	this.email = email;
    }
    public String getAuthRole() {
    	return this.authRole;
    }
    public void setAuthRole(String authRole) {
    	this.authRole = authRole;
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
