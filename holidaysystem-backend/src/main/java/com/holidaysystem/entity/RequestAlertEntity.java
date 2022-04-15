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

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.FutureOrPresent;

/**
 * RequestAlert entity
 * @author yauhen bichel yb3129h@gre.ac.uk Student Id 001185491
 *
 */
@Entity
@Table(name = "request_alert_queue", schema = "public")
public class RequestAlertEntity extends AuditEntity {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;
	@Column(name = "requestid")
    private UUID requestId;
	
	@FutureOrPresent
	@Column(name = "date")
	private LocalDateTime date;

    public void setId(UUID id) {this.id = id; }
    public UUID getId() {return this.id;}
    public void setRequestId(UUID requestId) {this.requestId = requestId; }
    public UUID getRequestId() {return this.requestId;}
    public void setDate(LocalDateTime date) {this.date = date; }
    public LocalDateTime getDate() {return this.date;}
}
