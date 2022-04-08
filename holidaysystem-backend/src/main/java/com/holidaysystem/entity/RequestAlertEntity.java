package com.holidaysystem.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
	@Column(name = "date")
	private LocalDateTime date;

    public void setId(UUID id) {this.id = id; }
    public UUID getId() {return this.id;}
    public void setRequestId(UUID requestId) {this.requestId = requestId; }
    public UUID getRequestId() {return this.requestId;}
    public void setDate(LocalDateTime date) {this.date = date; }
    public LocalDateTime getDate() {return this.date;}
}
