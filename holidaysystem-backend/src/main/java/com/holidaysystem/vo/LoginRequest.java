package com.holidaysystem.vo;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;

/**
 * Request model for login
 * @author yauhen bichel
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class LoginRequest implements Serializable {

    @Email
    private String email;
    
    @NotBlank
    private String password;
    
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
}
