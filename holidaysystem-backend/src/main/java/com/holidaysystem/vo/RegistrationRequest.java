package com.holidaysystem.vo;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;

/**
 * Request model for registration
 * @author yauhen bichel
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class RegistrationRequest implements Serializable {

    @Email
    private String email;
    
    @NotBlank
    private String password;
    
    @NotBlank
    private String authRole;
    
    public String getEmail() {
    	return this.email;
    }
    public void setEmail(String email) {
    	this.email = email;
    }
    public String getPassword() {
    	return this.password;
    }
    public void setPassword(String password) {
    	this.password = password;
    }
    public String getAuthRole() {
    	return this.authRole;
    }
    public void setAuthRole(String authRole) {
    	this.authRole = authRole;
    }
}
