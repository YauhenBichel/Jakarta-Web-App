package com.holidaysystem.entity;

import lombok.*;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;

@Entity
@Table(name = "users")
@Data
@EqualsAndHashCode(callSuper = false)
@Setter
@Getter
public class User extends AuditEntity {

	private static final long serialVersionUID = 1L;
	
	@Id
    @Column(name = "id")
    private UUID id;

    private String firstname;
    private String lastname;
    @Email
    private String email;

    public void setId(UUID id) {this.id = id; }
    public UUID getId() {return this.id;}
    public void setFirstname(String firstname) {this.firstname = firstname; }
    public String getFirstname() {return this.firstname;}
    public void setLastname(String lastname) {this.lastname = lastname; }
    public String getLastname() {return this.lastname;}
    public void setEmail(String email) {this.email = email; }
    public String getEmail() {return this.email;}
}
