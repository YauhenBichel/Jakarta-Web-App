package com.holidaysystem.entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.FutureOrPresent;
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
