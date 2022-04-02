package com.holidaysystem.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;

@Entity
@Table(name = "account", schema = "public")
public class AccountEntity extends AuditEntity {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;
    
    @Email
    private String email;
    private String password;

    public void setId(UUID id) {this.id = id; }
    public UUID getId() {return this.id;}
    public void setEmail(String email) {this.email = email; }
    public String getEmail() {return this.email;}
    public void setPassword(String password) {this.password = password; }
    public String getPassword() {return this.password;}
}
