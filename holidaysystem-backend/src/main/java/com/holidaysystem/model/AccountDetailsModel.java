/*
 *     Copyright 2022-2022 Yauhen Bichel yb3129h@gre.ac.uk OR bichel.eugen@gmail.com 
 *     Student Id 001185491
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *          http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
