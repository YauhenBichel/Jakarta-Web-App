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
package com.holidaysystem.entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.PastOrPresent;

import java.time.LocalDateTime;

/**
 * Entity with created and modified timestamps for tracing changes
 * @author yauhen bichel yb3129h@gre.ac.uk Student Id 001185491
 *
 */
@MappedSuperclass
public class AuditEntity {

    private static final long serialVersionUID = 1L;

    @PastOrPresent
    @Column(name = "created")
    private LocalDateTime created;

    @PastOrPresent
    @Column(name = "modified")
    private LocalDateTime modified;
    
    public void setCreated(LocalDateTime created) {
    	this.created = created;
    }
    public LocalDateTime getCreated() {
    	return this.created;
    }
    public void setModified(LocalDateTime modified) {
    	this.modified = modified;
    }
    public LocalDateTime getModified() {
    	return this.modified;
    }
}
