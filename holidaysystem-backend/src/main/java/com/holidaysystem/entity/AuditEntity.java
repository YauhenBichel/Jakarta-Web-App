package com.holidaysystem.entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/**
 * 
 * @author yauhen bichel
 *
 */
@MappedSuperclass
public class AuditEntity {

    private static final long serialVersionUID = 1L;

    @Column(name = "created")
    private LocalDateTime created;

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
