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
package com.holidaysystem.vo;

import java.io.Serializable;
import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

/**
 * Response model for request alert
 * @author yauhen bichel yb3129h@gre.ac.uk Student Id 001185491
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class RequestValidationResponse implements Serializable {
	
	@NotBlank
    private UUID requestId;
	
	@NotNull
    private Boolean valid;
    
    public UUID getRequestId() {
    	return this.requestId;
    }
    public void setRequestId(UUID requestId) {
    	this.requestId = requestId;
    }
    public Boolean getValid() {
    	return this.valid;
    }
    public void setValid(Boolean valid) {
    	this.valid = valid;
    }
}
