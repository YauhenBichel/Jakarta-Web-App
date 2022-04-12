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
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

/**
 * Employee entity
 * @author yauhen bichel yb3129h@gre.ac.uk Student Id 001185491
 *
 */
@Entity
@Table(name = "employee", schema = "public")
public class EmployeeEntity extends AuditEntity {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;
	
	@Column(name = "acountid")
    private UUID accountId;
	
	@NotBlank
	@Column(name = "role")
	private String role;
	
	@NotBlank
	@Column(name = "department")
	private String department;
	
	@NotBlank
	@Column(name = "firstname")
    private String firstName;
	
	@NotBlank
	@Column(name = "lastname")
	private String lastName;
	
	@PositiveOrZero
	@Column(name = "years")
    private Integer years;

    public void setId(UUID id) {this.id = id; }
    public UUID getId() {return this.id;}
    public void setDepartment(String department) {this.department = department; }
    public String getDepartment() {return this.department;}
    public void setRole(String role) {this.role = role; }
    public String getRole() {return this.role;}
    public void setAccountId(UUID accountId) {this.accountId = accountId; }
    public UUID getAccountId() {return this.accountId;}
    public void setFirstName(String firstName) {this.firstName = firstName; }
    public String getFirstName() {return this.firstName;}
    public void setLastName(String lastName) {this.lastName = lastName; }
    public String getLastName() {return this.lastName;}
    public void setYears(Integer years) {this.years = years; }
    public Integer getYears() {return this.years;}
}
