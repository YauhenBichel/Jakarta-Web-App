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
import javax.validation.constraints.PositiveOrZero;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;
import java.util.UUID;

/**
 * Prioritized  request model for holiday request
 * @author yauhen bichel yb3129h@gre.ac.uk Student Id 001185491
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class PrioritizedRequestModel implements Serializable {
     
    @NotBlank
    private UUID requestId;

    @PositiveOrZero
    private Integer takenDays;
    
    @PositiveOrZero
    private Integer requestedDays;
    
    public UUID getRequestId() {
    	return this.requestId;
    }
    public void setRequestId(UUID requestId) {
    	this.requestId = requestId;
    }
    public void setTakenDays(Integer takenDays) {this.takenDays = takenDays; }
    public Integer getTakenDays() {return this.takenDays;}
    public void setRequestedDays(Integer requestedDays) {this.requestedDays = requestedDays; }
    public Integer getRequestedDays() {return this.requestedDays;}
    
}
