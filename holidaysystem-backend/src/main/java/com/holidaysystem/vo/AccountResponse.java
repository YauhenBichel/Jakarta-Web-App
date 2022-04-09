package com.holidaysystem.vo;

import javax.validation.constraints.NotBlank;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;
import java.util.UUID;

/**
 * 
 * @author yauhen bichel
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class AccountResponse implements Serializable {
    
	@NotBlank
	private UUID id;
	
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
