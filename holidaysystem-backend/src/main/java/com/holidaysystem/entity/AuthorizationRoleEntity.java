package com.holidaysystem.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * AuthorizationRole entity
 * @author yauhen bichel yb3129h@gre.ac.uk Student Id 001185491
 *
 */
@Entity
@Table(name = "authorization_role", schema = "public")
public class AuthorizationRoleEntity {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;
    
	@NotBlank
    @Column(name = "name")
    private String name;
    
    public void setId(UUID id) {this.id = id; }
    public UUID getId() {return this.id;}
    public void setName(String name) {this.name = name; }
    public String getName() {return this.name;}
}
