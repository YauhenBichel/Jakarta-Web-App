package com.holidaysystem.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.holidaysystem.enumeration.AuthorizationRoleEnum;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;
import java.util.UUID;

/**
 * Account details model
 * @author yauhen bichel yb3129h@gre.ac.uk Student Id 001185491
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class AccountDetailsModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotBlank
    private UUID id;
	
	@NotBlank
    private String email;
	
	@NotNull
    private Boolean active;
	
	@NotNull
    private AuthorizationRoleEnum authRole;
    
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
    public Boolean getActive() {
    	return this.active;
    }
    public void setActive(Boolean active) {
    	this.active = active;
    }
    public AuthorizationRoleEnum getAuthRole() {		
    	return this.authRole;
    }
    public void setAuthRole(AuthorizationRoleEnum authRole) {
    	this.authRole = authRole;
    }
}
